<template>
	<div>
		<div class="crumbs">
			<el-breadcrumb separator="/">
				<el-breadcrumb-item>
					<i class="el-icon-lx-cascades"></i> 交药费表格
				</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="container">
			
			<el-table
			    :data="form"
			    border
			    class="table"
			    ref="multipleTable"
			    header-cell-class-name="table-header"
				:span-method="objectSpanMethod"
			>
			    <el-table-column prop="drug.drugName" label="药品名称" align="center"></el-table-column>
			    <el-table-column prop="drug.drugPrice" label="单价" align="center"></el-table-column>
			    <el-table-column prop="prescriptionDrugnum" label="药品数量" align="center"></el-table-column>
				<el-table-column prop="prescriptionTotalprice" label="总价" align="center"></el-table-column>
			</el-table>
			{{tip}}<el-input v-model="totalprice" style="width: 100px;" readonly :type="look"></el-input>
			<el-button type="primary" icon="el-icon-search" @click="money" 
				:style={display:editVisible}>缴费</el-button>
				
			
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
				editVisible: "none",
				pageTotal: 0,
				form: [],
				idx: -1,
				id: -1,
				content:'',
				departmentId:'',
				clickstate:false,
				look:'',
				reg:{
					registrationPatientId:1,
					patient:{patientName:''},
					department:{departmentName:''},
					registrationCreatedate:''
				},
				leng:'',
				code:'',
				totalprice:'',
				tip:'',
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
			//缴药费
			money(){
				var that = this;
				this.$axios.post('/updatePrescriptionById', this.qs.stringify({
						withCredentials: true,
						prescriptionCode:this.code
					}),)
					.then(function(response) {
						console.log(response);
						if(response.data.result.code='true'){
							that.getData();
						}
						
					})
					.catch(function(error) {
						console.log(error);
					});
			},
			// 一开始获取的数据
			getData() {
				var that = this;
				this.$axios.post('/findPrescriptionByCode', this.qs.stringify({
						withCredentials: true,
					}),)
					.then(function(response) {
						//console.log(that.query.pageSize);
						console.log(response);
						that.form = response.data.list;
						
						if(that.form!=null){
							that.tip="您需要缴纳的药费";
							that.leng = response.data.list.length;
							that.code = response.data.list[0].prescriptionCode;
							that.totalprice = response.data.list[0].prescriptionTotalprice;
							that.look="";	
							that.editVisible="block";
						}
						if(that.form==null){
							that.look="hidden";
							console.log(that.look);
							that.tip="";
							that.editVisible="none";
						}
						
					})
					.catch(function(error) {
						console.log(error);
					});
			},
			//合并单元格
			objectSpanMethod({ row, column, rowIndex, columnIndex }) {
				
			        if (columnIndex === 3) {//判断条件可以设置成你想合并的行的起始位置
			            return {
			              rowspan: this.leng,//单元所占行数 和上面判断条件一样
			              colspan: 1//单元所占列数
						};
			      };
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
