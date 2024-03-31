<template>
	<view class="helpforget">
		<view class="help_box">
			<uni-forms ref="regRef" :rules="rules" :model="regForm">
				<uni-forms-item name="username">
					<uni-easyinput v-model="regForm.username" placeholder="请输入昵称" />
				</uni-forms-item>
				<uni-forms-item name="email">
					<uni-easyinput v-model="regForm.email" placeholder="请输入邮箱" />
				</uni-forms-item>
				<uni-forms-item  name="password">
					<uni-easyinput type="password" v-model="regForm.password" placeholder="请输入密码" />
				</uni-forms-item>
				<uni-forms-item name="password1">
					<uni-easyinput type="password" v-model="regForm.password1" placeholder="再次确认密码" />
				</uni-forms-item>
			</uni-forms>
			<button class="button" @click="btpRegister" >确认修改</button>
		</view>
		
	</view>
</template>

<script>
	export default{
		data(){
			return{
				regForm:{
					username:"",
					password:"",
					email:""
				},
				rules: {
					username: {
						rules: [{
							required: true,
							errorMessage: '昵称不能为空'
						}]
					},
					email: {
						rules: [{
							required: true,
							errorMessage: '邮箱不能为空'
						}]
					},
					password: {
						rules: [{
							required: true,
							errorMessage: '密码不能为空'
						}]
					},
					password1: {
						rules: [{
							required: true,
							errorMessage: '再次输入密码'
						}]
					}
				},
			}
			
		},
		methods:{
			btpRegister(){
				//校验请求参数
				this.$refs.regRef.validate().then(res=>{
					//调用登录的方法
					console.log('表单错误信息：');
				}).catch(err =>{
					console.log('表单错误信息：', err);
				})
			}
		}
	}
</script>

<style lang="scss">
	.helpforget{
		width: 100vw;
		height: 100vh;
		box-sizing: border-box;
		background-image: url("../../static/back5.jpg");
		background-repeat: no-repeat;
		background-size: 100% 100%;
		.help_box{
			position:relative;
			top:380rpx;
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
			  background-color:#ccbcde;
			  border: none;
			  border-radius: 46px;
			  box-shadow: 0 9px #999;
			}

			.button:hover {background-color: #ccbcde}

			.button:active {
			  background-color: #ccbcde;
			  box-shadow: 0 5px #666;
			  transform: translateY(4px);
			}
		}
	}

</style>