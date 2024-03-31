<template>
	<uni-grid :column="5" :highlight="false":showBorder="false" :square="false" class='index-menu'>
		<uni-grid-item v-for="(item,index) in list" :key="index">
			<view class="choice-box">
				<image :src="item.src" @click="toHeritage(index)"></image>
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
						src: '/static/舞蹈2.png',
						text: '民间舞蹈',
					},{
						src: '/static/月琴.png',
						text: '民间音乐',
					},{
						src: '/static/脸谱.png',
						text: '传统戏剧',
					},{
						src: '/static/针织毛线.png',
						text: '手工技艺',
					},{
						src: '/static/书法笔触43.png',
						text: '民间美术',
					}
				]
			}
		},
		methods:{
			toHeritage(index){
				let heritageCategory;
				if(index==0){
					heritageCategory="民间舞蹈"
				}else if(index==1){
					heritageCategory="民间音乐"
				}else if(index==2){
					heritageCategory="传统戏剧"
				}else if(index==3){
					heritageCategory="手工技艺"
				}else if(index==4){
					heritageCategory="民间美术"
				}
				uni.navigateTo({
					url:"/pages/heritageSearch/heritageSearch?heritageCategory="+heritageCategory
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.index-menu{
		padding-top: 25px;
		font-size: 25upx;
		.choice-box{
			text-align: center;
			color: #8b572a;
			image{
				width: 90upx;
				height: 90upx;
				border-radius: 15px;
			}
		}
			
	}
</style>