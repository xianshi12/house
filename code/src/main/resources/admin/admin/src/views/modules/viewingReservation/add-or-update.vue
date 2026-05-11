<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="房屋名称" prop="propertyName" >
					<el-input v-model="ruleForm.fangwumingcheng" placeholder="房屋名称" clearable  :readonly="ro.fangwumingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="房屋名称" prop="propertyName" >
					<el-input v-model="ruleForm.fangwumingcheng" placeholder="房屋名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="房屋类型" prop="houseType" >
					<el-input v-model="ruleForm.fangwuleixing" placeholder="房屋类型" clearable  :readonly="ro.fangwuleixing"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="房屋类型" prop="houseType" >
					<el-input v-model="ruleForm.fangwuleixing" placeholder="房屋类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.fangwutupian" label="房屋图片" prop="propertyImage" >
					<file-upload
						tip="点击上传房屋图片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.fangwutupian?ruleForm.fangwutupian:''"
						@change="fangwutupianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.fangwutupian" label="房屋图片" prop="propertyImage" >
					<img v-if="ruleForm.fangwutupian.substring(0,4)=='http'&&ruleForm.fangwutupian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.fangwutupian" width="100" height="100">
					<img v-else-if="ruleForm.fangwutupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.fangwutupian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.fangwutupian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="出售价格" prop="salePrice" >
					<el-input v-model.number="ruleForm.chushoujiage" placeholder="出售价格" clearable  :readonly="ro.chushoujiage"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="出售价格" prop="salePrice" >
					<el-input v-model="ruleForm.chushoujiage" placeholder="出售价格" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="面积" prop="floorArea" >
					<el-input v-model="ruleForm.mianji" placeholder="面积" clearable  :readonly="ro.mianji"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="面积" prop="floorArea" >
					<el-input v-model="ruleForm.mianji" placeholder="面积" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="联系电话" prop="contactPhone" >
					<el-input v-model="ruleForm.lianxidianhua" placeholder="联系电话" clearable  :readonly="ro.lianxidianhua"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="联系电话" prop="contactPhone" >
					<el-input v-model="ruleForm.lianxidianhua" placeholder="联系电话" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="位置" prop="address" >
					<el-input v-model="ruleForm.weizhi" placeholder="位置" clearable  :readonly="ro.weizhi"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="位置" prop="address" >
					<el-input v-model="ruleForm.weizhi" placeholder="位置" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="开发商号" prop="developerCode" >
					<el-input v-model="ruleForm.kaifashanghao" placeholder="开发商号" clearable  :readonly="ro.kaifashanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="开发商号" prop="developerCode" >
					<el-input v-model="ruleForm.kaifashanghao" placeholder="开发商号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="开发商名" prop="developerName" >
					<el-input v-model="ruleForm.kaifashangming" placeholder="开发商名" clearable  :readonly="ro.kaifashangming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="开发商名" prop="developerName" >
					<el-input v-model="ruleForm.kaifashangming" placeholder="开发商名" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="预约时间" prop="appointmentTime" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.yuyueshijian" 
						type="datetime"
						:readonly="ro.yuyueshijian"
						:picker-options="yuyueshijianPickerOptions"
						placeholder="预约时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.yuyueshijian" label="预约时间" prop="appointmentTime" >
					<el-input v-model="ruleForm.yuyueshijian" placeholder="预约时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="账号" prop="account" >
					<el-input v-model="ruleForm.zhanghao" placeholder="账号" clearable  :readonly="ro.zhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="账号" prop="account" >
					<el-input v-model="ruleForm.zhanghao" placeholder="账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="姓名" prop="fullName" >
					<el-input v-model="ruleForm.xingming" placeholder="姓名" clearable  :readonly="ro.xingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="姓名" prop="fullName" >
					<el-input v-model="ruleForm.xingming" placeholder="姓名" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="手机" prop="phone" >
					<el-input v-model="ruleForm.shouji" placeholder="手机" clearable  :readonly="ro.shouji"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="手机" prop="phone" >
					<el-input v-model="ruleForm.shouji" placeholder="手机" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="登记时间" prop="registerTime" >
					<el-date-picker
						format="yyyy 年 MM 月 dd 日"
						value-format="yyyy-MM-dd"
						v-model="ruleForm.dengjishijian" 
						type="date"
						:readonly="ro.dengjishijian"
						placeholder="登记时间"
					></el-date-picker> 
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.dengjishijian" label="登记时间" prop="registerTime" >
					<el-input v-model="ruleForm.dengjishijian" placeholder="登记时间" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					提交
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	import { 
		isIntNumer,
		isMobile,
	} from "@/utils/validate";
	export default {
		data() {
			var validateMobile = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isMobile(value)) {
					callback(new Error("请输入正确的手机号码"));
				} else {
					callback();
				}
			};
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					fangwumingcheng : false,
					fangwuleixing : false,
					fangwutupian : false,
					chushoujiage : false,
					mianji : false,
					lianxidianhua : false,
					weizhi : false,
					kaifashanghao : false,
					kaifashangming : false,
					yuyueshijian : false,
					zhanghao : false,
					xingming : false,
					shouji : false,
					dengjishijian : false,
					sfsh : false,
					shhf : false,
				},
			
				ruleForm: {
					fangwumingcheng: '',
					fangwuleixing: '',
					fangwutupian: '',
					chushoujiage: '',
					mianji: '',
					lianxidianhua: '',
					weizhi: '',
					kaifashanghao: '',
					kaifashangming: '',
					yuyueshijian: '',
					zhanghao: '',
					xingming: '',
					shouji: '',
					dengjishijian: '',
					shhf: '',
				},

				yuyueshijianPickerOptions: {
					disabledDate(time) {
						return time.getTime() < Date.now();
					}
				},
				rules: {
					fangwumingcheng: [
						{ required: true, message: '房屋名称不能为空', trigger: 'blur' },
					],
					fangwuleixing: [
						{ required: true, message: '房屋类型不能为空', trigger: 'blur' },
					],
					fangwutupian: [
					],
					chushoujiage: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					mianji: [
						{ required: true, message: '面积不能为空', trigger: 'blur' },
					],
					lianxidianhua: [
					],
					weizhi: [
					],
					kaifashanghao: [
					],
					kaifashangming: [
					],
					yuyueshijian: [
						{ required: true, message: '预约时间不能为空', trigger: 'blur' },
					],
					zhanghao: [
					],
					xingming: [
					],
					shouji: [
						{ required: true, message: '手机不能为空', trigger: 'blur' },
						{ validator: validateMobile, trigger: 'blur' },
					],
					dengjishijian: [
					],
					sfsh: [
					],
					shhf: [
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
			this.ruleForm.dengjishijian = this.getCurDate()
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='propertyName'){
							this.ruleForm.fangwumingcheng = obj[o];
							this.ro.fangwumingcheng = true;
							continue;
						}
						if(o=='houseType'){
							this.ruleForm.fangwuleixing = obj[o];
							this.ro.fangwuleixing = true;
							continue;
						}
						if(o=='propertyImage'){
							this.ruleForm.fangwutupian = obj[o];
							this.ro.fangwutupian = true;
							continue;
						}
						if(o=='salePrice'){
							this.ruleForm.chushoujiage = obj[o];
							this.ro.chushoujiage = true;
							continue;
						}
						if(o=='floorArea'){
							this.ruleForm.mianji = obj[o];
							this.ro.mianji = true;
							continue;
						}
						if(o=='contactPhone'){
							this.ruleForm.lianxidianhua = obj[o];
							this.ro.lianxidianhua = true;
							continue;
						}
						if(o=='address'){
							this.ruleForm.weizhi = obj[o];
							this.ro.weizhi = true;
							continue;
						}
						if(o=='developerCode'){
							this.ruleForm.kaifashanghao = obj[o];
							this.ro.kaifashanghao = true;
							continue;
						}
						if(o=='developerName'){
							this.ruleForm.kaifashangming = obj[o];
							this.ro.kaifashangming = true;
							continue;
						}
						if(o=='appointmentTime'){
							this.ruleForm.yuyueshijian = obj[o];
							this.ro.yuyueshijian = true;
							continue;
						}
						if(o=='account'){
							this.ruleForm.zhanghao = obj[o];
							this.ro.zhanghao = true;
							continue;
						}
						if(o=='fullName'){
							this.ruleForm.xingming = obj[o];
							this.ro.xingming = true;
							continue;
						}
						if(o=='phone'){
							this.ruleForm.shouji = obj[o];
							this.ro.shouji = true;
							continue;
						}
						if(o=='registerTime'){
							this.ruleForm.dengjishijian = obj[o];
							this.ro.dengjishijian = true;
							continue;
						}
					}
				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.zhanghao!=''&&json.zhanghao) || json.zhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.zhanghao = json.zhanghao
							this.ro.zhanghao = true;
						}
						if(((json.xingming!=''&&json.xingming) || json.xingming==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.xingming = json.xingming
							this.ro.xingming = true;
						}
						if(((json.shouji!=''&&json.shouji) || json.shouji==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.shouji = json.shouji
							this.ro.shouji = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `kanfangyuyue/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.fangwutupian!=null) {
						this.ruleForm.fangwutupian = this.ruleForm.fangwutupian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					if(!this.ruleForm.id) {
						delete this.ruleForm.userid
					}
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
								}
							}
							
							await this.$http({
								url: `kanfangyuyue/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.kanfangyuyueCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
								} else {
									this.$message.error(data.msg);
								}
							});
						}
					});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.kanfangyuyueCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			fangwutupianUploadChange(fileUrls) {
				this.ruleForm.fangwutupian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 30px;
		width: 100%;
	}
	.add-update-preview {
		padding: 30px 25% 30px 15%;
		margin: 0 20px 0 40px;
		background: #FFFFFF;
		width: 100%;
		border-color: #eee;
		border-width: 0px 0 0;
		border-style: solid;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview ::v-deep  .el-form-item {
		border: 0px solid #eee;
		padding: 0;
		margin: 0 0 26px 0;
		display: inline-block;
		width: 100%;
	}
	.add-update-preview .el-form-item ::v-deep  .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		font-weight: 600;
		width: 180px;
		font-size: 18px;
		font-family: Source Han Sans, Source Han Sans;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item ::v-deep  .el-form-item__content {
		margin-left: 180px;
	}
	.add-update-preview .el-form-item span.text {
		border: 1px solid #a7a7a7;
		padding: 10px;
		color: #000000;
		display: block;
		font-size: 16px;
		line-height: 24px;
		border-radius: 0px;
		word-break: break-all;
		background: #fff;
		width: 100%;
		text-align: left;
		min-width: 50%;
		height: auto;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input ::v-deep  .el-input__inner {
		border: 1px solid #C9C9C9;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input ::v-deep  .el-input__inner[readonly="readonly"] {
		border: 1px solid #C9C9C9;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number ::v-deep  .el-input__inner {
		text-align: left;
		border: 1px solid #C9C9C9;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number ::v-deep  .is-disabled .el-input__inner {
		text-align: left;
		border: 1px solid #C9C9C9;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number ::v-deep  .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number ::v-deep  .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select ::v-deep  .el-input__inner {
		border: 1px solid #C9C9C9;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-select ::v-deep  .is-disabled .el-input__inner {
		border: 1px solid #C9C9C9;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor ::v-deep  .el-input__inner {
		border: 1px solid #C9C9C9;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0  40px;
		color: #666;
		background: none;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor ::v-deep  .el-input__inner[readonly="readonly"] {
		border: 1px solid #C9C9C9;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0  40px;
		color: #666;
		background: none;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 1px solid #a7a7a7;
		border-radius: 0px;
		padding: 0 12px;
		color: #000000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		text-align: left;
		min-width: 50%;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			display: none;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 1px solid #a7a7a7;
		border-radius: 0px;
		padding: 0 12px;
		color: #000000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		text-align: left;
		min-width: 50%;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			display: none;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 1px solid #a7a7a7;
		border-radius: 0px;
		padding: 0 12px;
		color: #000000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		text-align: left;
		min-width: 50%;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview ::v-deep  .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview ::v-deep  .upload .upload-img {
		border: 1px  solid #BFBFBF;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview ::v-deep  .el-upload-list .el-upload-list__item {
		border: 1px  solid #BFBFBF;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview ::v-deep  .el-upload .el-icon-plus {
		border: 1px  solid #BFBFBF;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	.add-update-preview ::v-deep  .el-upload__tip {
		color: #666;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea ::v-deep  .el-textarea__inner {
		border: 1px solid #C9C9C9;
		border-radius: 0px;
		padding: 12px;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 14px;
		min-width: 100%;
		height: 120px;
	}
	.add-update-preview .el-textarea ::v-deep  .el-textarea__inner[readonly="readonly"] {
				border: 1px solid #C9C9C9;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: #fff;
				width: auto;
				font-size: 14px;
				min-width: 100%;
				height: 120px;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		width: 100%;
		.btn1 {
			border: 2px solid #000000;
			cursor: pointer;
			border-radius: 5px;
			margin: 0 10px 0 10px;
			color: #000000;
			background: #fff;
			width: 130px;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 2px solid #000000;
			cursor: pointer;
			border-radius: 5px;
			margin: 0 10px 0 10px;
			color: #000000;
			background: #fff;
			width: 130px;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 2px solid #000000;
			cursor: pointer;
			border-radius: 5px;
			margin: 0 10px 0 10px;
			color: #000000;
			background: #fff;
			width: 130px;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 2px solid #000000;
			cursor: pointer;
			border-radius: 5px;
			margin: 0 10px 0 10px;
			color: #000000;
			background: #fff;
			width: 130px;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 2px solid #000000;
			cursor: pointer;
			border-radius: 5px;
			margin: 0 10px 0 10px;
			color: #000000;
			background: #fff;
			width: 130px;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
