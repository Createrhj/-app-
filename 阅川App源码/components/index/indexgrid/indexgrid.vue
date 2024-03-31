<template>
	<uni-grid :column="4" :highlight="false":showBorder="false" :square="false"  class='index-menu'>
		<uni-grid-item v-for="(item,index) in list" :key="index">
			<view class="grid-item-box" >
				<image :src="item.src" @click="skip(index)" style="width: 40px;height: 40px;"></image>
				<view >{{item.text}}</view>
			</view>
		</uni-grid-item>
	</uni-grid>
</template>

<script>
	export default {
		data() {
			return{
				list: [{
						id: 0,
						src: '/static/grid_index/酒店住宿2.png',
						text: '酒店住宿',
					},{
						id: 1,
						src: '/static/grid_index/景点门票.png',
						text: '景点门票',
					},{
						id: 2,
						src: '/static/grid_index/剧场演出2.png',
						text: '剧场演出',
					},{
						id: 3,
						src: '/static/grid_index/博物馆.png',
						text: '博览四川',
					}
					
				]
			}
		},
		methods:{
			skip(newid){
				console.log(newid)
				if(newid == 0){
					uni.navigateTo({
						url: '/pages/hotelSearch/hotelSearch'
					})
				}
				if(newid == 1){
					uni.navigateTo({
						url: '/pages/scenerySearch/scenerySearch'
					})
				}
				if(newid == 2){
					uni.navigateTo({
						url: '/pages/theaterSearch/theaterSearch'
					})
				}
				if(newid == 3){
					uni.navigateTo({
						url: '/pages/museumSearch/museumSearch'
					})
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	.index-menu{
		
		.grid-item-box{
			text-align: center;
			color: #8b572a;
			image{
				width: 100upx;
				height: 100upx;
				border-radius: 15px;
			}
		}
		
	}

</style>