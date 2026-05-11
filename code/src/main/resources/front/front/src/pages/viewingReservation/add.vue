<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
			>
			<el-form-item class="add-item" label="房屋名称" prop="propertyName">
				<el-input v-model="ruleForm.fangwumingcheng" 
					placeholder="房屋名称" clearable :disabled=" false  ||ro.fangwumingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="房屋类型" prop="houseType">
				<el-input v-model="ruleForm.fangwuleixing" 
					placeholder="房屋类型" clearable :disabled=" false  ||ro.fangwuleixing"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="房屋图片" v-if="type!='cross' || (type=='cross' && !ro.fangwutupian)" prop="propertyImage">
				<file-upload
					tip="点击上传房屋图片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.fangwutupian?ruleForm.fangwutupian:''"
					@change="fangwutupianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="房屋图片" prop="propertyImage">
				<img v-if="ruleForm.fangwutupian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.fangwutupian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.fangwutupian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="出售价格" prop="salePrice">
				<el-input v-model.number="ruleForm.chushoujiage" 
					placeholder="出售价格" clearable :disabled=" false  ||ro.chushoujiage"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="面积" prop="floorArea">
				<el-input v-model="ruleForm.mianji" 
					placeholder="面积" clearable :disabled=" false  ||ro.mianji"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="联系电话" prop="contactPhone">
				<el-input v-model="ruleForm.lianxidianhua" 
					placeholder="联系电话" clearable :disabled=" false  ||ro.lianxidianhua"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="位置" prop="address">
				<el-input v-model="ruleForm.weizhi" 
					placeholder="位置" clearable :disabled=" false  ||ro.weizhi"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="开发商号" prop="developerCode">
				<el-input v-model="ruleForm.kaifashanghao" 
					placeholder="开发商号" clearable :disabled=" false  ||ro.kaifashanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="开发商名" prop="developerName">
				<el-input v-model="ruleForm.kaifashangming" 
					placeholder="开发商名" clearable :disabled=" false  ||ro.kaifashangming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="预约时间" prop="appointmentTime">
				<el-date-picker
					:disabled=" false  ||ro.yuyueshijian"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.yuyueshijian" 
					type="datetime"
					:picker-options="yuyueshijianPickerOptions"
					placeholder="预约时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="账号" prop="account">
				<el-input v-model="ruleForm.zhanghao" 
					placeholder="账号" clearable :disabled=" false  ||ro.zhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="姓名" prop="fullName">
				<el-input v-model="ruleForm.xingming" 
					placeholder="姓名" clearable :disabled=" false  ||ro.xingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="手机" prop="phone">
				<el-input v-model="ruleForm.shouji" 
					placeholder="手机" clearable :disabled=" false  ||ro.shouji"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="登记时间" prop="registerTime">
				<el-date-picker
					:disabled=" false  ||ro.dengjishijian"
					format="yyyy 年 MM 月 dd 日"
					value-format="yyyy-MM-dd"
					v-model="ruleForm.dengjishijian" 
					type="date"
					placeholder="登记时间">
				</el-date-picker> 
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn"  type="primary" @click="onSubmit">
					<span class="icon iconfont "></span>
					<span class="text">提交</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont "></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
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
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
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
				},

				// 只能选择今天之后日期
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
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
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
						{ validator: this.$validate.isMobile, trigger: 'blur' },
					],
					dengjishijian: [
					],
					sfsh: [
					],
					shhf: [
					],
				},
				centerType: false,
			};
		},
		computed: {



		},
		components: {
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
			this.ruleForm.dengjishijian = this.getCurDate()
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
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
							this.ruleForm.fangwutupian = obj[o]?obj[o].split(",")[0]:'';
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
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
						if((json.zhanghao!=''&&json.zhanghao) || json.zhanghao==0){
							this.ruleForm.zhanghao = json.zhanghao;
							this.ro.zhanghao = true;
						}
						if((json.xingming!=''&&json.xingming) || json.xingming==0){
							this.ruleForm.xingming = json.xingming;
							this.ro.xingming = true;
						}
						if((json.shouji!=''&&json.shouji) || json.shouji==0){
							this.ruleForm.shouji = json.shouji;
							this.ro.shouji = true;
						}
					}
				});

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			info() {
				this.$http.get(`kanfangyuyue/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				if(!this.ruleForm.id) {
					delete this.ruleForm.userid
				}
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									for (var o in obj){
										if(o==statusColumnName){
											obj[o] = statusColumnValue;
										}
									}
									var table = localStorage.getItem('crossTable');
									await this.$http.post(table+'/update', obj).then(res => {});
								}
							}
						}


						await this.$http.post(`kanfangyuyue/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
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
				this.$router.go(-1);
			},
			fangwutupianUploadChange(fileUrls) {
				this.ruleForm.fangwutupian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 0 0 20px;
		margin: 0px auto;
		color: #666;
		background: #fff;
		width: 1200px;
		font-size: 16px;
		position: relative;
		.add-update-form {
			margin: 20px 0 0;
			width: 100%;
			position: relative;
			.add-item.el-form-item {
				border-radius: 0px;
				padding: 6px 0 0;
				margin: 0 0 20px 0;
				background: none;
				border-color: #475a8310;
				border-width:  0 0 0px;
				border-style: solid;
				::v-deep  .el-form-item__label {
					padding: 0 10px 0 0;
					color: #666;
					font-weight: 500;
					width: 180px;
					font-size: inherit;
					line-height: 40px;
					text-align: right;
				}
				::v-deep  .el-form-item__content {
					margin-left: 180px;
				}
				.el-input {
					width: auto;
				}
				.el-input ::v-deep  .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input ::v-deep  .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: rgba(85, 85, 127, 1.0);
					background: none;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number ::v-deep  .el-input__inner {
					text-align: left;
					border: 1px solid #ddd;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number ::v-deep  .is-disabled .el-input__inner {
					text-align: left;
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: rgba(85, 85, 127, 1.0);
					background: none;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number ::v-deep  .el-input-number__decrease {
					display: none;
				}
				.el-input-number ::v-deep  .el-input-number__increase {
					display: none;
				}
				.el-select {
					width: auto;
				}
				.el-select ::v-deep  .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 0px;
					padding: 0 10px;
					color: inherit;
					width: 100%;
					font-size: 16px;
					min-width: inherit !important;
					height: 40px;
				}
				.el-select ::v-deep  .is-disabled .el-input__inner {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 10px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-date-editor {
					width: auto;
				}
				.el-date-editor ::v-deep  .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 0px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-date-editor ::v-deep  .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				::v-deep  .el-upload--picture-card {
					background: transparent;
					border: 0;
					border-radius: 0;
					width: auto;
					height: auto;
					line-height: initial;
					vertical-align: middle;
				}
				::v-deep  .upload .upload-img {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					background: #fff;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
				}
				::v-deep  .el-upload-list .el-upload-list__item {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					background: #fff;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
					font-size: 14px;
					line-height: 1.8;
				}
				::v-deep  .el-upload .el-icon-plus {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					background: #fff;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
				}
				::v-deep  .el-upload__tip {
					color: #888;
					font-size: 16px;
				}
				.el-textarea ::v-deep  .el-textarea__inner {
					border: 1px solid #ddd;
					border-radius: 0px;
					padding: 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					min-height: 150px;
					min-width: 48%;
					height: auto;
				}
				.el-textarea ::v-deep  .el-textarea__inner[readonly="readonly"] {
					border: 0px solid #ddd;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 12px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: auto;
					font-size: 16px;
					min-height: 150px;
					min-width: 50%;
					height: auto;
				}
				::v-deep  .el-input__inner::placeholder {
					color: inherit;
					font-size: inherit;
				}
				::v-deep  textarea::placeholder {
					color: inherit;
					font-size: inherit;
				}
				.editor {
					background-color: #fff;
					border-radius: 0;
					padding: 0;
					box-shadow: none;
					margin: 0;
					width: 100%;
					min-height: 350px;
					border-color: #ccc;
					border-width: 1px;
					border-style: solid;
					height: auto;
				}
				.upload-img {
					object-fit: cover;
					width: 100px;
					height: 100px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 20px;
					margin: 0;
					color: #333;
					background: #475a8330;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 34px;
					height: 34px;
				}
				.viewBtn:hover {
				}
				.unviewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					color: #333;
					display: inline-block;
					font-size: 14px;
					line-height: 34px;
					border-radius: 4px;
					outline: none;
					background: #ddd;
					width: auto;
					height: 34px;
				}
				.unviewBtn:hover {
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 20px 0;
				.submitBtn {
					border: 0;
					cursor: pointer;
					padding: 0 24px 0 30px;
					margin: 0 20px 0 0;
					display: inline-block;
					font-size: 16px;
					line-height: 44px;
					border-radius: 4px;
					background: #0066D4;
					width: auto;
					text-align: center;
					min-width: 120px;
					height: 44px;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.submitBtn:hover {
					color: #fff;
					.icon {
					}
					.text {
					}
				}
				.closeBtn {
					border: 1px solid #0066D450;
					cursor: pointer;
					padding: 0 24px 0 30px;
					margin: 0 20px 0 0;
					color: #0066D4;
					display: inline-block;
					font-size: 16px;
					line-height: 44px;
					border-radius: 4px;
					background: #fff;
					width: auto;
					text-align: center;
					min-width: 120px;
					height: 44px;
					.icon {
						color: #333;
					}
					.text {
						color: #0066D4;
					}
				}
				.closeBtn:hover {
					color: #80593c;
					.icon {
					}
					.text {
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
