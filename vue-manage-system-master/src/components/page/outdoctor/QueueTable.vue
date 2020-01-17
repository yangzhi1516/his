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
                <el-table-column prop="queueId" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="registration.patient.patientName" label="病人姓名"></el-table-column>
                <el-table-column prop="registration.patient.patientSex" label="性别"></el-table-column>
                <el-table-column prop="registration.patient.patientAge" label="年龄"></el-table-column>
                <el-table-column prop="registration.patient.patientTelephone" label="联系电话"></el-table-column>
                <el-table-column prop="registration.registrationCreatedate" :formatter="formatDate" label="挂号时间"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-service"
                            style="color: rgb(118,238,0);"
                            @click = "openBoxCabin(scope.$index, scope.row)"
                        >病史</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.$index, scope.row)"
                        >开药</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="handleDelete(scope.$index, scope.row)"
                        >结束</el-button>
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
        <el-dialog title="填写药方" :visible.sync="editVisible" width="80%">
            <el-form ref="form" :model="form" label-width="10px">
                    <div class="drag-box">
                    <div class="drag-box-item">
                        <div class="item-title">药物</div>
                        <draggable v-model="todo" @remove="removeHandle" :options="dragOptions">
                            <transition-group tag="div" id="todo" class="item-ul">
                                <div v-for="item in todo" class="drag-list" :key="item.drugName">
                                    {{item.drugName}}               库存:{{item.drugNumber}}
                                </div>
                            </transition-group>
                        </draggable>
                    </div>
                    <el-form-item>
                    <div class="drag-box-item">
                        <div class="item-title">药方</div>
                        <draggable v-model="doing" @remove="removeHandle" :options="dragOptions">
                            <transition-group tag="div" id="doing" class="item-ul">
                                <div v-for="item in doing" class="drag-list" :key="item.drugName">
                                    {{item.drugName}}               库存:{{item.drugNumber}}
                                </div>
                            </transition-group>
                        </draggable>
                    </div>  
                     </el-form-item>
                     <el-form-item>
                    <div class="drag-box-item">
                        <div class="item-title">剂量</div>
                        <draggable v-model="doing" @remove="removeHandle" :options="dragOptions">
                            <transition-group tag="div" id="doing" class="item-ul">
                                <div v-for="item in doing" class="drag-list" :key="item.drugName" :resizable="false"> 
                                    <el-input
                                        placeholder="请输入数目"
                                        prefix-icon="el-icon-edit"
                                        v-model.number="item.drugnum">
                                    </el-input>
                                </div>
                            </transition-group>
                        </draggable>
                    </div>  
                     </el-form-item>
                </div>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 病史弹出框 -->
        <el-dialog title="病史记录" :visible.sync="checkVisible" width="60%">
        <div class="container">
                <el-table
                :data="history"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
            >
                <el-table-column prop="historyId" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="historyInformation" label="病况"></el-table-column>
                <el-table-column prop="staff.staffName" label="诊治医生"></el-table-column>
                <el-table-column prop="historyCreatedate" :formatter="formatDate2" label="诊治时间"></el-table-column>
                </el-table-column>
            </el-table>
                <el-form ref="reason" label-width="100px"> 
                    <el-form-item label="填写诊断情况:">
                        <el-input v-model="reason"></el-input>
                    </el-form-item>
                    <el-form-item>
                    <el-button type="primary" @click="checkEdit">提 交</el-button>
                    <el-button @click="checkVisible = false">取 消</el-button>
                    </el-form-item>
                </el-form>
        </div>
                
        </el-dialog>

    </div>
</template>

<script>
import { fetchData } from '../../../api/index';
import draggable from 'vuedraggable';
export default {
    name: 'basetable',
    data() {
        return {
            dragOptions:{
                animation: 120,
                scroll: true,
                group: 'sortlist',
                ghostClass: 'ghost-style'
            },
            todo: [
                    {
                        drugId: 1,
                        drugName: '药名'
                    },
                ],
                doing: [
                    {   
                        drugId: '',
                        drugName:'例如:药品',
                        drugnum:'1'
                    },
                ],
                prescription:[
                ],
            query: {
                pageIndex: 1,
                pageSize: 5
            },
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            checkVisible: false,
            pageTotal: 0,
            form: {},
            history:[],
            reason:'',
            idx: -1,
            id: -1,
            patientid:'',
            open:'开药',
            write:'医嘱'
        };
    },
    created() {
        this.getData();
        
    },
    components:{
            draggable
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
        },
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            var that = this;
            //console.log(that._data.tableData);
            this.$axios.post('/findAllByDepId', {
                    withCredentials:true
                    })
                        .then(function (response) {
                            //console.log(that.query.pageSize);
                            console.log(response);
                            that._data.tableData = response.data.list;
                            //console.log(that._data.tableData);
                            that._data.pageTotal = response.data.list.length;

                            //console.log(that._data.pageTotal);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
        },
        removeHandle(event){
            console.log(event);
            this.$message.success(`从 ${event.from.id} 移动到 ${event.to.id} `);
        },
        // 触发搜索按钮
        handleSearch() {
            this.$set(this.query, 'pageIndex', 1);
            this.getData();
        },
        // 删除操作
        handleDelete(index, row) {
            var that = this;
            this.form = row;
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            })
                .then(() => {
                    this.$axios.post('/delQueue', {
                            regId:that.form.registration.registrationId
                        })
                        .then(function (response) {
                            console.log(response);
                            that.$message.success('删除成功');
                            that.tableData.splice(index, 1);
                            that.$axios.post('/updateWorkById', {
                                withCredentials:true
                            })
                            .then(function (response) {
                                console.log(response);
                            })  
                            .catch(function (error) {
                                console.log(error);
                            })
                        })
                        .catch(function (error) {
                            console.log(error);
                        })
                    
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
            var that=this;
            this.idx = index;
            this.form = row;
            this.editVisible = true;
            this.$axios.post('/findAllDrug', {
                withCredentials:true,
            })
                        .then(function (response) {
                            that._data.todo = response.data.list;
                       })
                        .catch(function (error) {
                            console.log(error);
                        });
        },
        // 保存编辑
        saveEdit() {
            var that = this;
            this.editVisible = false;
            this.$message.success(`修改第 ${this.idx + 1} 行成功`);
            this.$set(this.tableData, this.idx, this.form);
            for(var i=1;i<this.doing.length;i++){
                this.prescription.push({prescriptionDrugnum:null, prescriptionDrugId:null}); 
                this.prescription[i-1].prescriptionDrugnum=this.doing[i].drugnum;
                this.prescription[i-1].prescriptionDrugId=this.doing[i].drugId;
            }
            this.prescription.push({prescriptionPatientId:this.form.registration.patient.patientId });
            this.$axios.post('/addPrescription', {
                prescription:this.prescription
            })
                        .then(function (response) {
                            console.log(response);
                             for(var i=0;i<that.doing.length;i++){
                                that.prescription.pop();
                                that.doing.pop();
                            }
                            that.open ='已开药';
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
        },
        //提交病史
        checkEdit(){
            var that = this;
            this.checkVisible = false;
            this.$axios.post('/addHistory', this.qs.stringify({
                historyInformation:this.reason,
                historyPatientId:this.patientid
            }),)
            .then(function (response) {
                console.log(response);
                that.history = response.data.list;
                that.write = '已填写';
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
        openBoxCabin(index, row){
            var that = this;
            this.checkVisible=true;
            this.patientid=row.registration.registrationPatientId;
            this.$axios.post('/findHistoryByPatientId', this.qs.stringify({
                id:row.registration.registrationPatientId
            }),)
            .then(function (response) {
                console.log(response);
                that.history = response.data.list;
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        formatDate(row, column) {
            if(row.registration!=null){
            // 获取单元格数据
            let data = row.registration.registrationCreatedate;
            let dt = new Date(data);
            return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
            }else{
                return "未签到";
            }
           
        },
        formatDate2(row, column){
            if(row.historyCreatedate!=null){
                let data = row.historyCreatedate;
                let dt = new Date(data);
                return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
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
<style scoped>
    .drag-box{
        display: flex;
        user-select: none;
    }
    .drag-box-item {
        flex: 1;
        max-width: 330px;
        min-width: 300px;
        background-color: #eff1f5;
        margin-right: 16px;
        border-radius: 6px;
        border: 1px #e1e4e8 solid;
    }
    .item-title{
        padding: 8px 8px 8px 12px;
        font-size: 14px;
        line-height: 1.5;
        color: #24292e;
        font-weight: 600;
    }
    .item-ul{
        padding: 0 8px 8px;
        height: 500px;
        overflow-y: scroll;
    }
    .item-ul::-webkit-scrollbar{
        width: 0;
    }
    .drag-list {
        border: 1px #e1e4e8 solid;
        padding: 10px;
        margin: 5px 0 10px;
        list-style: none;
        background-color: #fff;
        border-radius: 6px;
        cursor: pointer;
        -webkit-transition: border .3s ease-in;
        transition: border .3s ease-in;
    }
    .drag-list:hover {
        border: 1px solid #20a0ff;
    }
    .drag-title {
        font-weight: 400;
        line-height: 25px;
        margin: 10px 0;
        font-size: 22px;
        color: #1f2f3d;
    }
    .ghost-style{
        display: block;
        color: transparent;
        border-style: dashed
    }
</style>