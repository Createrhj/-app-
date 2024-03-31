<template>
	<view class="login_home">
		<!-- login字样 -->
		<view class="login_title">LOGIN</view>
		<!-- 登录表单校验 -->
			<view class="login_box">
				<uni-forms ref="loginRef" :rules="login_rules" :model="loginForm">
					<uni-forms-item  name="userName">
						<uni-easyinput v-model="loginForm.userName" placeholder="请输入账号" />
					</uni-forms-item>
					<uni-forms-item  name="userPassword">
						<uni-easyinput type="password" v-model="loginForm.userPassword" placeholder="请输入密码" />
					</uni-forms-item>
				</uni-forms>
				<button type="primary" @click="btpLogin" class="button">登录</button>
				<!-- 	忘记密码 -->
				<view class="forget">
					<text @click="toForget">忘记密码</text>
				</view>
			</view>
		<!-- 没有账号？注册 -->
		<view class="register-section">
			<text style="color: #FFFFFF; font-weight: bold;">没有账号？</text>
			<text @click="toRegister" style="color: #FFFFFF; font-weight: bold;">马上注册</text>
		</view>
	</view>
</template>

<script>
	import LoginApi from '../../api/LoginApi.js'
	
	export default{
		data(){
			return{
				//登录输入的表单
				loginForm:{
					userName:"",
					userPassword:""
				},
				//登录表单的校验规则
				login_rules: {
					userName: {
						rules: [{
							required: true,
							errorMessage: '账号不能为空'
						}]
					},
					userPassword: {
						rules: [{
							required: true,
							errorMessage: '密码不能为空'
						}]
					}
				},
			}
	
		},
		methods:{
			btpLogin(){
				//校验请求参数
				this.$refs.loginRef.validate().then(res=>{
					
					this.submitLogin()
				}).catch(err =>{
					console.log('表单错误信息：', err);
				})
			},
			async submitLogin(){
				LoginApi.userLogin(this.loginForm.userName,this.loginForm.userPassword).then(res=>res.data).then(res=>{
					if(res.code===200){
						uni.switchTab({
							url:'/pages/index/index'
						})
						setTimeout(function() {
						  uni.showToast({
						  	title: `用户登录成功`,
						  	icon: 'none'
						  })
						}, 100)
						localStorage.setItem("CurUser",JSON.stringify(res.data.CurUser))
					}else{
						uni.showToast({
							title: `登录失败`,
							icon: 'none'
						})
					}
				})
			},
			toRegister(){
				uni.navigateTo({
					url:'/pages/mine/register'
				})
			},
			toForget(){
				uni.navigateTo({
					url:'/pages/mine/forget'
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	/deep/ .uni-forms-item__error[data-v-61dfc0d0] {
		color: #ebeddf !important;
		
	}

	.login_home{
		width: 100vw;
		height: 100vh;
		box-sizing: border-box;
		background-image: url("../../static/bg2.jpg");
		background-repeat:no-repeat;
		background-size:100% 100%;
		.login_title{
			color: #fff;
			font-size: 160upx;
			position: relative;
			top: 250rpx;
			left: 20rpx;
			opacity: 0.7;   //字体透明度
		}
		.login_box{
			position:relative;
			top:500rpx ;
			margin: 0 40px;
			.button {
			  width: 100%;
			  height: 45px;
			  display: inline-block;
			  //padding: 5px 25px;
			  align-items: center;
			  justify-content: center;
			  cursor: pointer;
			  text-align: center;   
			  text-decoration: none;
			  outline: none;
			  color: #fff;
			  background-color:#ddbb99;
			  border: none;
			  border-radius: 46px;
				
			}

			.button:hover {background-color:#ddbb99;}

			.button:active {
			  background-color:#ddbb99;;
			  //box-shadow: 0 5px #666;
			  transform: translateY(4px);
			}
			.forget{
				color: #FFFFFF;
				text-align: center;
				height: 100rpx;
				line-height: 80rpx;
			}
		}
		.register-section{
			position: absolute;
			bottom: 50rpx;
			width: 100%;
			text-align: center;
			text{
				color: #FFFFFF;
			}
		}
	}
</style>