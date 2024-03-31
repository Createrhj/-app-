<template>
	<view class="box">
			<image class="img" src="../../static/启动页.jpg" mode="scaleToFill"></image>
		</view>
</template>

<script>
	export default{
		data(){
			return{
				CurUser:[],
				// 倒计时秒数
				seconds:3,
				// 定时器的 Id
				timer: null
			}
		},
		onLoad() {
			this.CurUser=localStorage.getItem("CurUser")
			this.delayNavigate(this.CurUser)
		},
		methods:{
			// 延迟导航到 my 页面
			delayNavigate() {
			  // 把 data 中的秒数重置成 3 秒
			  this.seconds = 3
			  this.timer = setInterval(() => {
				this.seconds--
				if (this.seconds <= 0) {
				  clearInterval(this.timer)
				  if(this.CurUser!=null){
					  uni.switchTab({
					  	url: '/pages/index/index'
					  })
				  }else if(this.CurUser==null){
					uni.navigateTo({
						url: '/pages/mine/login'
					})  
				  }
				  return
				}
				
			  }, 1000)
			},
		},
	}
</script>

<style lang="scss">
	.box {
		width: 100vw;
		height: 100vh;
 
 
		.img {
			width: 100%;
			height: 100%;
		}
	}
</style>
