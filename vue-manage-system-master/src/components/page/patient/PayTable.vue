<template>
	<div>
		<div class="crumbs">
			<el-breadcrumb separator="/">
				<el-breadcrumb-item>
					<i class="el-icon-lx-cascades"></i> 交住院费表格
				</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="container">
			
			<el-form ref="form"  label-width="80px" id="methods">
				
				<el-form-item label="总的住院费" label-width="120px">
					<el-input v-model="content" style="width: 100px;" readonly></el-input>
				</el-form-item>
				<el-form-item label="已交住院费" label-width="120px">
					<el-input v-model="pay" style="width: 100px;" readonly></el-input>
				</el-form-item>
				<el-form-item label="未交住院费" label-width="120px">
					<el-input v-model="rest" style="width: 100px;" readonly></el-input>
				</el-form-item>
				<el-form-item label="缴纳费用:" label-width="120px">
					<el-input type="number" v-model="paypay" style="width: 100px;" 
						@input="onInputBlur"></el-input>
				</el-form-item>
				<el-button type="primary" icon="el-icon-search" @click="money" :disabled=clickstate>缴费</el-button>
			</el-form>
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
				content:'',
				departmentId:'',
				clickstate:false,
				look:true,
				reg:{
					registrationPatientId:1,
					patient:{patientName:''},
					department:{departmentName:''},
					registrationCreatedate:''
				},
				pay:'',
				rest:'',
				paypay:''
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
			// 缴纳住院费操作
			money(){
				var that = this;
				if(this.pay===null){
					this.pay = this.paypay
				}else{
					this.pay = parseInt(this.pay)+parseInt(this.paypay)
				}
				this.$axios.post('/patientUpdateHospitalization', this.qs.stringify({
						withCredentials: true,
						paypay:this.paypay,//正在缴纳的住院费
						hospitalizationRest:this.content-this.pay,//未交住院费
						hospitalizationPay:this.pay//已交住院费
					}),)
					.then(function(response) {
						console.log(response);
						if(response.data.code=='true'){
							that.$message.success('缴费成功');
							that.getData();
						}
						
					})
					.catch(function(error) {
						console.log(error);
					});
			},
			//失去焦点判断输入的金额
			onInputBlur(val){
				if(val>this.rest){
					val=this.rest;
					this.paypay=this.rest;
				}
				if(val<=0){
					val=0;
					this.paypay=0;
				}
			},
			
			// 一开始获取的数据
			getData() {
				var that = this;
				//console.log(that._data.tableData);
				this.$axios.post('/findAll', {
						withCredentials: true
					})
					.then(function(response) {
						//console.log(that.query.pageSize);
						//console.log(response);
						that.form = response.data.listh;
						console.log(that.form);
						that.content=that.form[0].hospitalizationCost;
						that.pay=that.form[0].hospitalizationPay;
						that.rest=that.form[0].hospitalizationRest;
						if(that.rest==0){
							that.clickstate=true;
						}
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
