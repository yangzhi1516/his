<template>
	<div>
		<div class="crumbs">
			<el-breadcrumb separator="/">
				<el-breadcrumb-item>
					<i class="el-icon-lx-cascades"></i> 住院表格
				</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="container">
			
			<el-form ref="form"  label-width="80px" id="methods">
				<el-form-item label="选择诊室">
					<el-select v-model="form.departmentName" placeholder="请选择">
						 <el-option
						        v-for="(item,index) in form"
						        :key="index"
						        :label="item.departmentName"
						        :value="item.departmentName"
								
						></el-option>
					</el-select>
					
				</el-form-item>
				<el-form-item label="住院原因">
					<el-input v-model="ptext" placeholder="请输入内容"></el-input>
				</el-form-item>
				<el-button type="primary" icon="el-icon-search" @click="addhosp" :disabled=clickstate>提交</el-button>
			</el-form>
			<!-- 编辑弹出框 -->
			
			<el-dialog title="挂号单" :visible.sync="editVisible" width="50%">
			    <el-form ref="reg" :model="reg" label-width="120px">
					<el-form-item label="病人姓名">
						<el-input readonly v-model="reg.patient.patientName"></el-input>
					</el-form-item>
					<el-form-item label="挂号的科室">
						<el-input readonly v-model="reg.department.departmentName"></el-input>
					</el-form-item>
					<el-form-item label="挂号的时间">
						<el-input readonly  v-model="reg.registrationCreatedate"></el-input>
					</el-form-item>
			    </el-form>
			    <span slot="footer" class="dialog-footer">
			        <el-button @click="editVisible = false">取 消</el-button>
			        <el-button @click="editVisible = false">确 定</el-button>
			    </span>
			</el-dialog>
			
			
		</div>
		
	</div>
</template>

<script>
	import {
		fetchData
	} from '../../../api/index';
	import draggable from 'vuedraggable';
	export default {
		name: 'basetable',
		data() {
			return {
				openIsDisabled: false,
				tableData: [],
				multipleSelection: [],
				delList: [],
				editVisible: false,
				pageTotal: 0,
				form: {},
				idx: -1,
				id: -1,
				ptext:'',
				departmentId:'',
				clickstate:false,
				look:true,
				reg:{
					registrationPatientId:1,
					patient:{patientName:''},
					department:{departmentName:''},
					registrationCreatedate:''
				},
				content:''
			};
		},
		created() {
			this.getData();

		},
		components: {
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
			//申请住院
			addhosp(){
				var that = this;
				this.$axios.post('/addHospitalizationByPatient', this.qs.stringify({
						withCredentials: true,
						patientDepartmentId:this.departmentId,
						hospitalizationReason:this.ptext
						
					}),)
					.then(function(response) {
						console.log(response);
						if(response.data.code=='true'){
							that.$message.success('申请成功');
							that.look=false;
							that.clickstate=true;
						}
						
					})
					.catch(function(error) {
						console.log(error);
					});
					
			},
			// 一开始获取的数据
			getData() {
				var that = this;
				//console.log(that._data.tableData);
				this.$axios.post('/findAllDepartment', {
						withCredentials: true
					})
					.then(function(response) {
						//console.log(that.query.pageSize);
						//console.log(response);
						that._data.tableData = response.data.result;
						that.form=that._data.tableData;		
						that.content=that.form.departmentCost;
					})
					.catch(function(error) {
						console.log(error);
					});
			},
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
	.drag-box {
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

	.item-title {
		padding: 8px 8px 8px 12px;
		font-size: 14px;
		line-height: 1.5;
		color: #24292e;
		font-weight: 600;
	}

	.item-ul {
		padding: 0 8px 8px;
		height: 500px;
		overflow-y: scroll;
	}

	.item-ul::-webkit-scrollbar {
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

	.ghost-style {
		display: block;
		color: transparent;
		border-style: dashed
	}
</style>
