<template>
	<div>

		<div class="container">
			<el-form class='rgs-form animate__animated animate__' v-if="pageFlag=='register'" ref="registerForm" :model="registerForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">房屋出售系统</p></div>
					<el-form-item class="list-item" v-if="tableName=='user'" prop="account">
						<div class="label" :class="changeRules('account')?'required':''">账号：</div>
						<el-input v-model="registerForm.account"  placeholder="请输入账号" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'" prop="password">
						<div class="label" :class="changeRules('password')?'required':''">密码：</div>
						<el-input v-model="registerForm.password" type="password" placeholder="请输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'" prop="password2">
						<div class="label" :class="changeRules('password')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.password2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'" prop="fullName">
						<div class="label" :class="changeRules('fullName')?'required':''">姓名：</div>
						<el-input v-model="registerForm.fullName"  placeholder="请输入姓名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'" prop="gender">
						<div class="label" :class="changeRules('gender')?'required':''">性别：</div>
						<el-select v-model="registerForm.gender" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in userGenderOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'" prop="phone">
						<div class="label" :class="changeRules('phone')?'required':''">手机：</div>
						<el-input v-model="registerForm.phone"  placeholder="请输入手机" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'" prop="idCard">
						<div class="label" :class="changeRules('idCard')?'required':''">身份证：</div>
						<el-input v-model="registerForm.idCard"  placeholder="请输入身份证" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'" prop="avatar">
						<div class="label" :class="changeRules('avatar')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.avatar?registerForm.avatar:''"
							@change="avatarUploadChange1"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'" prop="developerCode">
						<div class="label" :class="changeRules('developerCode')?'required':''">开发商号：</div>
						<el-input v-model="registerForm.developerCode"  placeholder="请输入开发商号" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'" prop="password">
						<div class="label" :class="changeRules('password')?'required':''">密码：</div>
						<el-input v-model="registerForm.password" type="password" placeholder="请输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'" prop="password2">
						<div class="label" :class="changeRules('password')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.password2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'" prop="developerName">
						<div class="label" :class="changeRules('developerName')?'required':''">开发商名：</div>
						<el-input v-model="registerForm.developerName"  placeholder="请输入开发商名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'" prop="gender">
						<div class="label" :class="changeRules('gender')?'required':''">性别：</div>
						<el-select v-model="registerForm.gender" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in developerGenderOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'" prop="contactPhone">
						<div class="label" :class="changeRules('contactPhone')?'required':''">联系电话：</div>
						<el-input v-model="registerForm.contactPhone"  placeholder="请输入联系电话" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'" prop="avatar">
						<div class="label" :class="changeRules('avatar')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.avatar?registerForm.avatar:''"
							@change="avatarUploadChange2"
						></file-upload>
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<el-button class="register_btn" type="primary" @click="submitForm('registerForm')">注册</el-button>
						</div>
						<div class="register-btn2">
							<router-link class="has_btn" to="/login">已有账号，直接登录</router-link>
						</div>
					</div>
				</div>
				<div class="idea1"></div>
				<div class="idea2"></div>
			</el-form>
		</div>
	</div>
</div>
</template>

<script>
	import 'animate.css';

export default {
    //数据集合
    data() {
		return {
            pageFlag : '',
			tableName: '',
			registerForm: {},
			forgetForm: {},
			rules: {},
			requiredRules: {},
            userGenderOptions: [],
            developerGenderOptions: [],
		}
    },
	mounted() {
		if(this.$route.query.pageFlag=='register'){
			this.tableName = this.$route.query.role;
			if(this.tableName=='user'){
				this.registerForm = {
					account: '',
					password: '',
					password2: '',
					fullName: '',
					gender: '',
					phone: '',
					idCard: '',
					avatar: '',
				}
			}
			if(this.tableName=='developer'){
				this.registerForm = {
					developerCode: '',
					password: '',
					password2: '',
					developerName: '',
					gender: '',
					contactPhone: '',
					avatar: '',
				}
			}
			if ('user' == this.tableName) {
				this.rules.account = [{ required: true, message: '请输入账号', trigger: 'blur' }];
				this.requiredRules.account = [{ required: true, message: '请输入账号', trigger: 'blur' }]
			}
			if ('user' == this.tableName) {
				this.rules.password = [{ required: true, message: '请输入密码', trigger: 'blur' }];
				this.requiredRules.password = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			this.userGenderOptions = "男,女".split(',');
			if ('user' == this.tableName) {
				this.rules.phone = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
			}
			if ('user' == this.tableName) {
				this.rules.idCard = [{ required: true, validator: this.$validate.isIdCard, trigger: 'blur' }];
			}
			if ('developer' == this.tableName) {
				this.rules.developerCode = [{ required: true, message: '请输入开发商号', trigger: 'blur' }];
				this.requiredRules.developerCode = [{ required: true, message: '请输入开发商号', trigger: 'blur' }]
			}
			if ('developer' == this.tableName) {
				this.rules.password = [{ required: true, message: '请输入密码', trigger: 'blur' }];
				this.requiredRules.password = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('developer' == this.tableName) {
				this.rules.developerName = [{ required: true, message: '请输入开发商名', trigger: 'blur' }];
				this.requiredRules.developerName = [{ required: true, message: '请输入开发商名', trigger: 'blur' }]
			}
			this.developerGenderOptions = "男,女".split(',');
			if ('developer' == this.tableName) {
				this.rules.gender = [{ required: true, message: '请输入性别', trigger: 'blur' }];
				this.requiredRules.gender = [{ required: true, message: '请输入性别', trigger: 'blur' }]
			}
			if ('developer' == this.tableName) {
				this.rules.contactPhone = [{ required: true, validator: this.$validate.isMobileNotNull, trigger: 'blur' }];
				this.requiredRules.contactPhone = [{ required: true, message: '请输入联系电话', trigger: 'blur' }]
			}
		}
	},
    created() {
		this.pageFlag = this.$route.query.pageFlag;
    },
    //方法集合
    methods: {
		changeRules(name){
			if(this.requiredRules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
        // 下二随
		avatarUploadChange1(fileUrls) {
			this.registerForm.avatar = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},
		avatarUploadChange2(fileUrls) {
			this.registerForm.avatar = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},

		// 多级联动参数


		submitForm(formName) {
			this.$refs[formName].validate((valid) => {
				if (valid) {
					var url=this.tableName+"/register";
					if(this.registerForm.password!=this.registerForm.password2) {
						this.$message.error(`两次密码输入不一致`);
						return
					}
					const submitData = Object.assign({}, this.registerForm);
					delete submitData.password2;
					this.$http.post(url, submitData).then(res => {
						if (res.data.code === 0) {
							this.$message({
								message: '注册成功',
								type: 'success',
								duration: 1500,
								onClose: () => {
									this.$router.push('/login');
								}
							});
						} else {
							this.$message.error(res.data.msg);
						}
					}).catch(() => {
						this.$message.error('请求失败，请确认后端服务已启动并且接口地址正确');
					});
				} else {
					return false;
				}
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		}
    }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.container {
		background-repeat: no-repeat;
		background-size: 100% 105% !important;
		background: url(http://codegen.caihongy.cn/20241010/b69d118332f9468884d1a35f8134443d.jpg);
		display: flex;
		width: 100%;
		min-height: 100vh;
		justify-content: center;
		align-items: center;
		background-position: center center;
		position: relative;
		background: url(http://codegen.caihongy.cn/20241010/b69d118332f9468884d1a35f8134443d.jpg);
		.rgs-form {
			padding: 20px 150px 0 80px;
			margin: 0 40% 10% 0;
			z-index: 10;
			background: #fff;
			display: flex;
			width: 800px;
			justify-content: flex-start;
			align-items: center;
			height: auto;
			.rgs-form2 {
				width: 100%;
				.title {
					margin: 0 0 30px -80px;
					color: #333;
					background: none;
					font-weight: 600;
					width: calc(100% + 230px);
					font-size: 20px;
					line-height: 44px;
					text-align: center;
				}
				.subtitle {
					margin: 0 0 10px 0;
					text-shadow: 4px 4px 2px rgba(64, 158, 255, .5);
					color: rgba(64, 158, 255, 1);
					width: 100%;
					font-size: 20px;
					line-height: 44px;
					text-align: center;
				}
				.list-item {
					padding: 0;
					margin: 0 auto 20px;
					background: none;
					width: 100%;
					position: relative;
					::v-deep .el-form-item__content {
						padding: 0 0 0 120px;
						display: block;
						width: 100%;
						.label {
							padding: 0 5px 0 0;
							z-index: 999;
							color: #333;
							left: 0;
							width: 120px;
							font-size: 16px;
							line-height: 40px;
							position: absolute !important;
							text-align: right;
						}
						
						.required {
							position: relative;
						}
						.required::after{
							margin: 0 10px 0 0;
							color: red;
							left: 110px;
							position: inherit;
							content: "*";
						}
						.el-input {
							flex: 1;
							width: 100%;
						}
						.el-input .el-input__inner {
							border: 1px solid #0A75E750;
							border-radius: 0;
							padding: 0 10px;
							color: #666;
							flex: 1;
							width: calc(100% - 0px);
							font-size: 15px;
							height: 40px;
						}
						.el-input .el-input__inner:focus {
							border: 1px solid #0A75E7;
							border-radius: 0;
							padding: 0 10px;
							outline: none;
							color: #666;
							width: calc(100% - 0px);
							font-size: 15px;
							height: 40px;
						}
						.el-input-number {
							flex: 1;
							width: 100%;
						}
						.el-input-number .el-input__inner {
							text-align: left;
							border: 1px solid #0A75E750;
							border-radius: 0;
							padding: 0 10px;
							color: #666;
							flex: 1;
							width: calc(100% - 0px);
							font-size: 15px;
							height: 40px;
						}
						.el-input-number .el-input-number__decrease {
							display: none;
						}
						.el-input-number .el-input-number__increase {
							display: none;
						}
						.el-select {
							flex: 1;
							width: calc(100% - 0px);
						}
						.el-select .el-input__inner {
							border: 1px solid #0A75E750;
							border-radius: 0;
							padding: 0 10px;
							color: #666;
							width: 100%;
							font-size: 15px;
							height: 40px;
						}
						.el-select .el-input__inner:focus {
							border: 1px solid #0A75E7;
							border-radius: 0;
							padding: 0 10px;
							outline: none;
							color: #666;
							width: 100%;
							font-size: 15px;
							height: 40px;
						}
						.el-date-editor {
							flex: 1;
							width: calc(100% - 0px);
						}
						.el-date-editor .el-input__inner {
							border: 1px solid #0A75E750;
							border-radius: 0;
							padding: 0 10px 0 30px;
							color: #666;
							width: 100%;
							font-size: 15px;
							height: 40px;
						}
						.el-date-editor .el-input__inner:focus {
							border: 1px solid #0A75E7;
							border-radius: 0;
							padding: 0 10px 0 30px;
							outline: none;
							color: #666;
							width: 100%;
							font-size: 15px;
							height: 40px;
						}
						.el-upload--picture-card {
							background: transparent;
							border: 0;
							border-radius: 0;
							width: auto;
							height: auto;
							line-height: initial;
							vertical-align: middle;
						}
						.upload .upload-img {
							border: 1px solid #0A75E750;
							cursor: pointer;
							border-radius: 0px;
							color: #0A75E760;
							background: #fff;
							width: 80px;
							font-size: 24px;
							line-height: 50px;
							text-align: center;
							height: 50px;
						}
						.el-upload-list .el-upload-list__item {
							border: 1px solid #0A75E750;
							cursor: pointer;
							border-radius: 0px;
							color: #0A75E760;
							background: #fff;
							width: 80px;
							font-size: 24px;
							line-height: 50px;
							text-align: center;
							height: 50px;
							font-size: 14px;
							line-height: 1.8;
						}
						.el-upload .el-icon-plus {
							border: 1px solid #0A75E750;
							cursor: pointer;
							border-radius: 0px;
							color: #0A75E760;
							background: #fff;
							width: 80px;
							font-size: 24px;
							line-height: 50px;
							text-align: center;
							height: 50px;
						}
						.el-upload__tip {
							color: #666;
							font-size: 15px;
						}
						.emailInput {
							border: 1px solid #0A75E750;
							border-radius: 0px 0 0 0px;
							padding: 0 10px;
							margin: 0;
							color: #606266;
							background: #fff;
							width: calc(100% - 0px);
							font-size: 15px;
							height: 40px;
						}
						.emailInput:focus {
							border: 1px solid #0A75E7;
							border-radius: 0px 0 0 0px;
							padding: 0 10px;
							outline: none;
							color: #606266;
							width: calc(100% - 0px);
							font-size: 15px;
							height: 40px;
						}
						.el-btn {
							border: 1px solid #0A75E7;
							cursor: pointer;
							border-radius: 0 0px 0px 0;
							padding: 0 10px;
							margin: 0;
							color: #fff;
							background: #0A75E7;
							width: 110px;
							font-size: 15px;
							border-width: 1px 1px 1px 0;
							float: right;
							height: 40px;
						}
						.el-btn:hover {
							opacity: 1;
						}
						
						.el-input__inner::placeholder {
							color: #999;
							font-size: 15px;
						}
						input::placeholder {
							color: #999;
							font-size: 15px;
						}
						.editor {
							margin: 0;
							background: #fff;
							width: calc(100% - 0px);
							height: auto;
						}
					}
				}
				.register-btn {
					margin: 20px auto;
					width: 100%;
				}
				.register-btn1 {
					padding: 0 0 0 120px;
					width: 100%;
				}
				.register-btn2 {
					padding: 0 0 0 120px;
					margin: 10px auto;
					width: 100%;
					text-align: right;
				}
				.register_btn {
					border: 0;
					cursor: pointer;
					border-radius: 0px;
					padding: 0 30px;
					margin: 0 0px;
					color: #0A75E7;
					background: #caeeff;
					font-weight: 600;
					letter-spacing: 4px;
					width: auto;
					font-size: 18px;
					height: 40px;
				}
				.register_btn:hover {
				}
				.has_btn {
					cursor: pointer;
					padding: 0;
					color: #555;
					display: inline-block;
					text-decoration: none;
					font-size: 15px;
					line-height: 40px;
				}
				.has_btn:hover {
					opacity: 0.8;
				}
			}
			.idea1 {
				background: red;
				display: none;
				width: 100%;
				height: 40px;
			}
			.idea2 {
				background: blue;
				display: none;
				width: 100%;
				height: 40px;
			}
		}
	}
	
	::-webkit-scrollbar {
		display: none;
	}
</style>
