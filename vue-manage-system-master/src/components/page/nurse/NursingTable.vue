<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 基础表格
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
                <!--<el-select v-model="query.address" placeholder="地址" class="handle-select mr10">
                    <el-option key="1" label="广东省" value="广东省"></el-option>
                    <el-option key="2" label="湖南省" value="湖南省"></el-option>
                </el-select>-->
                <el-input v-model="query.name" placeholder="病人姓名" class="handle-input mr10"></el-input>
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
                <el-table-column prop="patientId" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="patientName" label="病人姓名"></el-table-column>
                <el-table-column prop="patientAge" label="病人年龄"></el-table-column>
                <el-table-column prop="patientTelephone" label="联系电话"></el-table-column>
                <el-table-column prop="patientSex" label="病人性别"></el-table-column>
                <el-table-column prop="hospitalization.hospitalizationReason" label="申请住院原因"></el-table-column>
                <el-table-column prop="hospitalization.hospitalizationIndate" :formatter="formatDate" label="病人入院时间"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.$index, scope.row)"
                        >护理</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-delete"
                            @click="handleDelete(scope.$index, scope.row)"
                        >查看护理</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-delete"
                            @click="handleCheck(scope.$index, scope.row)"
                        >查看病人病史</el-button>
                        
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
        <el-dialog title="请输入需要护理的内容" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="护理内容">
                    <el-input  v-model="form.nursingInformation"></el-input>
                </el-form-item>
                <!--<el-form-item label="进货数量">
                    <el-input v-model="form.drugNumber"></el-input>
                </el-form-item>-->
                
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 查看护理弹出框-->
        <el-dialog title="查看护理" :visible.sync="editVisible1" width="30%">
        	<el-table
        		:data="nursingData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
        	>
        		<el-table-column prop="nursingInformation" label="护理内容"></el-table-column>
        		<el-table-column prop="nursingCreatedate":formatter="formatDate2" label="护理时间"></el-table-column>
        		<el-table-column prop="patient.patientName" label="病人姓名"></el-table-column>
        	</el-table>
          
        </el-dialog>
        <!-- 查看病史-->
        <el-dialog title="病人病史列表" :visible.sync="editVisible2" width="30%">
        	<el-table
        		:data="historyData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
        	>
        		<el-table-column prop="historyInformation" label="病史内容"></el-table-column>
        		<el-table-column prop="historyCreatedate":formatter="formatDate3" label="诊治时间"></el-table-column>
        		<!--<el-table-column prop="patient.patientName" label="病人姓名"></el-table-column>
        		-->
        	</el-table>
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
                pageSize: 5
            },
            tableData: [],
            historyData: [],
            nursingData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            editVisible1: false,
            editVisible2: false,
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
	        )}else{
	        	return ;
	        }
        }
        
        
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            var that = this;
            //console.log(that._data.tableData);
            this.$axios.post('/findPatientByHoszatstate', this.qs.stringify({
            			withCredentials:true,
            			name:this.query.name
            			})
     			       	)	
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
        // 触发搜索按钮
        handleSearch() {
            this.$set(this.query, 'pageIndex', 1);
            this.getData();
        },
        // 删除操作
        handleDelete(index, row) {
        	var that = this;
        //	console.log(this.editVisible1);
            this.idx = index;
            this.form = row;
            this.editVisible1 = true;
			this.$axios.post('/findNursingByPatientId', this.qs.stringify({	
                withCredentials:true,
                nursingPatientId:this.form.patientId
           	}),
            )
                        .then(function (response) {
                            console.log(response);
                            that._data.nursingData = response.data.list;
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
        },
        //查看病史
        handleCheck(index, row) {
        	var that = this;
        	console.log(this.editVisible2);
            this.idx = index;
            this.form = row;
            this.editVisible2 = true;
			this.$axios.post('/findHistoryByPatientId', this.qs.stringify({	
                withCredentials:true,
                id:this.form.patientId
           	}),
            )
                        .then(function (response) {
                            console.log(response);
                            that._data.historyData = response.data.list;
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
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
        	console.log(this.editVisible);
            this.idx = index;
            this.form = row;
            this.editVisible = true;
      
        },
        // 保存编辑
        saveEdit() {
            var that = this;
            this.editVisible = false;
            this.$message.success(`护理病人: ${this.form.patientName } 成功`);
            this.$set(this.tableData, this.idx, this.form);
            console.log(this.form);
            this.$axios.post('/addNursing', this.qs.stringify({	
                withCredentials:true,
                nursingInformation:this.form.nursingInformation,
                nursingPatientId:this.form.patientId
           	}),
//          this.qs.stringify()
            )
                        .then(function (response) {
                            //console.log(that.query.pageSize);
                            //console.log(response);
                           	 //that._data.tableData = response.data.list;
                            //console.log(that._data.tableData);
                            //that._data.pageTotal = Math.ceil((response.data.list.length/that.query.pageSize)+1);
                            //console.log(that._data.pageTotal);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageIndex', val);
            this.getData();
        },
        formatDate(row, column) {
            if(row.hospitalization.hospitalizationIndate!=null){
            // 获取单元格数据
            let data = row.hospitalization.hospitalizationIndate;
            let dt = new Date(data);
            return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
            }else{
                return "正在申请入院";
            }
        },
          formatDate2(row, column) {
            if(row.nursingCreatedate!=null){
            // 获取单元格数据
            let data = row.nursingCreatedate;
            let dt = new Date(data);
            return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
            }else{
                return "无";
            }
        },
         formatDate3(row, column) {
            if(row.historyCreatedate!=null){
            // 获取单元格数据
            let data = row.historyCreatedate;
            let dt = new Date(data);
            return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
            }else{
                return "无";
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
