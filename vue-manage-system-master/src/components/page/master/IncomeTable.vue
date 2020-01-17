<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 收入表格
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                    type="primary"
                    icon="el-icon-delete"
                    class="handle-del mr10"
                    @click="delAllSelection"
                >批量删除</el-button>
                <el-select v-model="query.address" placeholder="地址" class="handle-select mr10">
                    <el-option key="1" label="广东省" value="广东省"></el-option>
                    <el-option key="2" label="湖南省" value="湖南省"></el-option>
                </el-select>
                <el-input v-model="query.name" placeholder="用户名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div>
            <el-table
                :data="showTable"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="incomeId" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="incomeRegistration" label="挂号收入"></el-table-column>
                <el-table-column prop="incomeWork" label="诊疗收入"></el-table-column>
                <el-table-column prop="incomeHospitalization" label="住院收入"></el-table-column>
                <el-table-column prop="incomePrescription" label="药费收入"></el-table-column>
                <el-table-column prop="incomeCreatedate" :formatter="formatDate" label="收入日期"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.$index, scope.row)"
                        >编辑</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="handleDelete(scope.$index, scope.row)"
                        >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.pageIndex"
                    :page-size="query.pageSize"
                    :total="pageTotal"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="修改库存" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="药品名称">
                    <el-input readonly v-model="form.drugName"></el-input>
                </el-form-item>
                <el-form-item label="进货数量">
                    <el-input v-model="form.drugNumber"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { fetchData } from '../../../api/index';
export default {
    name: 'basetable',
    data() {
        return {
            query: {
                pageIndex: 1,
                pageSize: 8
            },
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            pageTotal: 0,
            form: {},
            idx: -1,
            id: -1
        };
    },
    created() {
        this.getData();
        
    },
    computed: {
    //showTable计算属性通过slice方法计算表格当前应显示的数据
        showTable() {
            if(this.tableData!=null){
                return this.tableData.slice(
                    (this.query.pageIndex - 1) * this.query.pageSize,
                    this.query.pageIndex * this.query.pageSize
                );
                }else{
                    return ;
                }
        }
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            var that = this;
            //console.log(that._data.tableData);
            this.$axios.post('/checkAll', {withCredentials:true})
                        .then(function (response) {
                            //console.log(that.query.pageSize);
                            console.log(response);
                            that._data.tableData = response.data.list;
                            //console.log(that._data.tableData);
                            that._data.pageTotal =response.data.list.length;
                            //console.log(that._data.pageTotal);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
        },
        // 触发搜索按钮
        handleSearch() {
            this.$set(this.query, 'pageIndex', 1);
        },
        // 删除操作
        handleDelete(index, row) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            })
                .then(() => {
                    this.$message.success('删除成功');
                    this.tableData.splice(index, 1);
                })
                .catch(() => {});
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        delAllSelection() {
            const length = this.multipleSelection.length;
            let str = '';
            this.delList = this.delList.concat(this.multipleSelection);
            for (let i = 0; i < length; i++) {
                str += this.multipleSelection[i].name + ' ';
            }
            this.$message.error(`删除了${str}`);
            this.multipleSelection = [];
        },
        // 编辑操作
        handleEdit(index, row) {
            this.idx = index;
            this.form = row;
            this.editVisible = true;
        },
        // 保存编辑
        saveEdit() {
            var that = this;
            this.editVisible = false;
            this.$message.success(`修改第 ${this.idx + 1} 行成功`);
            this.$set(this.tableData, this.idx, this.form);
            console.log(this.form);
            this.$axios.post('/updateDrugNumberById', {
                withCredentials:true,
                drugNumber:this.form.drugNumber,
                drugId:this.form.drugId
            })
                        .then(function (response) {
                            //console.log(that.query.pageSize);
                            //console.log(response);
                            that._data.tableData = response.data.list;
                            //console.log(that._data.tableData);
                            that._data.pageTotal = response.data.list.length;
                            //console.log(that._data.pageTotal);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageIndex', val);
        },
        formatDate(row, column) {
            if(row.incomeCreatedate!=null){
            // 获取单元格数据
            let data = row.incomeCreatedate;
            let dt = new Date(data);
            return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
            }else{
                return "未签到";
            }
        }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
