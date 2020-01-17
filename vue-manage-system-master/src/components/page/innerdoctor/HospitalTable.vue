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
                <el-table-column prop="hospitalizationId" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="patient.patientName" label="病人姓名"></el-table-column>
                <el-table-column prop="patient.patientSex" label="性别"></el-table-column>
                <el-table-column prop="patient.patientAge" label="年龄"></el-table-column>
                <el-table-column prop="patient.patientTelephone" label="联系电话"></el-table-column>
                <el-table-column prop="hospitalizationReason" label="住院原因"></el-table-column>
                <el-table-column prop="hospitalizationCreatedate" :formatter="formatDate" label="申请时间"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-service"
                            style="color: rgb(118,238,0);"
                            :disabled = " openIsDisabled "
                            @click="handleAdd(scope.$index, scope.row)"
                        >{{state}}</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.$index, scope.row)"
                        >{{live}}</el-button>
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

        <!-- 医嘱弹出框 -->
        <el-dialog title="填写医嘱" :close-on-click-modal="false" :visible.sync="addVisible" width="50%">

            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="症状:">
                    <el-input readonly v-model="form.hospitalizationReason"></el-input>
                </el-form-item>
                <el-form-item label="医嘱内容:">
                    <el-input type="textarea" autosize v-model="content"></el-input>
                </el-form-item>
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveAdd">确 定</el-button>
            </span>
        </el-dialog>

         <!-- 安排弹出框 -->
        <el-dialog title="具体安排" :visible.sync="editVisible" width="50%">         
            <el-table
                :data="rooms"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="roomId" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="roomName" label="房间名"></el-table-column>
                <el-table-column prop="roomRestnum" label="剩余床位"></el-table-column>
                <el-table-column prop="roomUsenum" label="已用床位"></el-table-column>
                <el-table-column prop="roomTotalnum" label="总床位数"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-edit"
                            @click="saveEdit(scope.$index, scope.row)"
                        >入住</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="roomEdit"
                        >取消</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>

         <el-dialog title="填写住院天数" :close-on-click-modal="false" :visible.sync="dayVisible" width="50%"> 
            <el-form ref="days" :rules="rules" :model="days" label-width="80px">    
                <el-form-item label="天数:" :required="true">
                 <el-input v-model.number="days.day"></el-input>
                 <el-button type="primary" @click="dayAdd">确 定</el-button>
                </el-form-item>
            </el-form>
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
                rooms:[],
            query: {
                pageIndex: 1,
                pageSize: 5
            },
            state:"医嘱",
            live: "安排",
            openIsDisabled:false,
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            addVisible:false,
            pageTotal: 0,
            form: {},
            idx: -1,
            id: -1,
            content:'',
            roomid:'',
            dayVisible:false,
            days:{
                day:'',
            },
            rules: {
                info: [{message: '必须填写', trigger: 'blur'}],
            },
            hospitalization:{}
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
            }
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            var that = this;
            //console.log(that._data.tableData);
            this.$axios.post('/findApplyHospitalization', {
                    withCredentials:true
                    })
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
                       })
                        .catch(function (error) {
                            console.log(error);
                        });
                    
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
        //填写医嘱
        handleAdd(index, row) {
            var that=this;
            this.idx = index;
            this.form = row;
            this.addVisible = true;
        },
        // 编辑操作
        handleEdit(index, row) {
            var that=this;
            this.idx = index;
            this.form = row;
            this.hospitalization = row;
            this.editVisible = true;
            this.$axios.post('/findRoomByDepartmentId',
            that.qs.stringify({
                withCredentials:true,
                id:localStorage.getItem('departmentId')
                }),)
                    .then(function (response) {
                        console.log(response);
                        that.rooms=response.data.list;
                        console.log(that.rooms);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
        },
        // 保存医嘱
        saveAdd() {
            this.state = "已填写";
            var that = this;
            this.addVisible = false;
            this.$set(this.tableData, this.idx, this.form);
            this.form.content=this.content;
            console.log(this.form);
            this.$axios.post('/addAdvice', 
                that.qs.stringify({
                    withCredentials:true,
                    adviceInformation:this.form.content,
                    advicePatientId:this.form.patient.patientId
                }),
            )
                        .then(function (response) {
                           console.log(response);
                           that.state="已填写";
                           that.$message.success(`医嘱提交成功`);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
           
        },
        roomEdit() {
            this.editVisible = false;
        },
        //添加天数
        dayAdd(){
            var that = this;
            this.dayVisible = false;
            this.$axios.post('/updateHospitalization',
                    that.qs.stringify({
                        withCredentials:true,
                        hospitalizationDays:that.days.day,
                        hospitalizationRoomId:that.roomid,
                        hospitalizationId:that.hospitalization.hospitalizationId
                        }),)
                            .then(function (response) {
                                console.log(response);
                                that.$message.success(`入住成功`);   
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
        },
        // 保存编辑
        saveEdit(index, row) {
            if(row.roomRestnum<=0){
                this.$message.success(`该房间已住满`);   
            }else{
            this.live = "已入住";
            var that = this;
            this.editVisible = false;
            var room = row;   
            this.roomid = room.roomId;
            this.$axios.post('/updateRoomNumById', that.qs.stringify({
                withCredentials:true,
                roomid:room.roomId
                }),)
                        .then(function (response) {
                           console.log(response);
                           that.dayVisible = true;
                        })
                       .catch(function (error) {
                            console.log(error);
                        });
            }      
           
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageIndex', val);
            this.getData();
        },
        formatDate(row, column) {
            if(row.hospitalizationCreatedate!=null){
            // 获取单元格数据
            let data = row.hospitalizationCreatedate;
            let dt = new Date(data);
            return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
            }else{
                return "未挂号";
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