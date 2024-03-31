<template>
	<view class="goodsSearch">
		<uni-search-bar radius="100" placeholder="其输入搜索内容" clearButton="none" />
		<view class="goodsTotal">
			<view class="filter" style="font-size: 16px;margin-top: -15px;margin-bottom: 10px;">
				<text style="color: #631216;font-weight: bold;">综合</text>
				<text style="color: #631216;font-weight: bold;">价格</text>
				<text style="color: #631216;font-weight: bold;">销量</text>
				<text @click="openPopup" style="color: #631216;font-weight: bold;">筛选</text>
			</view>
			<view class="goodsList" @click="toSceneryDetail(scenery.sceneryID)" v-for="scenery in sceneryData" :key="scenery.seceneryID">
				<image :src="imgPrefix+scenery.sceneryImg" mode="aspectFill"></image>
				<view class="goodsInfor">
					<view class="inforList" style="margin-top: 2px;font-weight: bold;">
						<text>{{scenery.sceneryName}}</text>
					</view>
					<view class="inforList">
						<uni-tag 
						style="background-color: #e67762; border-color: #e67762; color: #fff;"
						:inverted="true" text="2022年度景点" type="error" size="small"/>
					</view>
					<view class="inforList">
						<uni-tag 
						 style="background-color: #cb523e; border-color: #cb523e; color: #fff;"
						 :text="scenery.sceneryCount" type="primary" size="mini"/>
						<text style="margin-right: 4px;">{{scenery.sceneryCount}}评分</text>
						<text>3.3万人收藏</text>
					</view>
					<view class="inforList">
						<text>{{scenery.sceneryCity}}</text>
					</view>
					<text class="price">￥{{scenery.sceneryPrice}}元</text>
				</view>
			</view>
		</view>
		
		<uni-popup ref="popup" type="top">
			<view class="filterPopup">
				<text>景点类型</text>
				<view class="filterType">
					<button>自然风光</button>
					<button>名胜古迹</button>
					<button>城市观光</button>
				</view>
				<view class="filterType">
					<button>文化体验</button>
					<button>户外活动</button>
					<button>休闲娱乐</button>
				</view>
				<text style="margin-top: 20px;">价格区间</text>
				<view class="filterType">
					<input placeholder="自定义最低价"/>
					<uni-icons type="arrow-right" size="20"></uni-icons>
					<input placeholder="自定义最高价"/>
				</view>
				<text style="margin-top: 20px;">景点地区</text>
				<view class="filterType">
					<button>成都</button>
					<button>绵阳</button>
					<button>乐山</button>
				</view>
				<view class="filterType">
					<button>宜宾</button>
					<button>凉山</button>
					<button>甘孜</button>
				</view>
				<view class="filterType">
					<button class="btn">取消</button>
					<button class="btn">确定筛选</button>
				</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
	import TravelApi from '../../api/TravelApi.js'
	export default {
		data() {
			return {
				sceneryData:[],
				searchMap:{
					sceneryName:'',
					sceneryCategory:'',
					sceneryCity:'',
					lowPrice:'',
					highPrice:'',
				},
				imgPrefix:'http://localhost:9527/app/sceneryImg/'
			}
		},
		methods: {
			openPopup(){
			// 通过组件定义的ref调用uni-popup方法 ,如果传入参数 ，type 属性将失效 ，仅支持 ['top','left','bottom','right','center']
			this.$refs.popup.open()
			},
			allScenery(){
				TravelApi.allScenery(this.searchMap).then(res=>res.data).then(res=>{
					this.sceneryData=res.data
					for (const key in this.sceneryData){
						const imgList=this.sceneryData[key].sceneryImg.split('/')
						this.sceneryData[key].sceneryImg=imgList[0]
					}
				})
			},
			toSceneryDetail(sceneryID){
				uni.navigateTo({
					url: '/pages/sceneryDetail/sceneryDetail?sceneryID='+sceneryID
				})
			},
		},
		mounted(){
			this.allScenery()
		}
	}
</script>

<style lang="scss">
text{
	color: #683632;
}
button::after{ border: none;} 
.btn{
	margin-left: 15px;
	margin-right: 10px;
	margin-top: 20px;
	width: 150px;
	height: 40px;
	border-radius: 10px;
	font-size: 17px
}
button{
	width: 80px;
	height: 30px;
	margin: 5px 0px 0px 10px;
	border-radius: 20px;
	border:0px;
	font-size: 13px;
	color: #EAEEF1;
	background-color: #CB523E;
}
input{
	color: #EAEEF1;
	background-color: #CB523E;
	width: 120px;
	height: 30px;
	margin-right: 10px;
	margin-left: 20px;
	margin-top: 5px;
	text-align: center;
	border-radius: 15px;
}
.filterPopup{
	width: 100%;
	height: 750rpx;
	padding-top: 10px;
	background-color: #FFFFFF;
	border-bottom-left-radius: 15px;
	border-bottom-right-radius: 15px;
	display: flex;
	flex-direction: column;
	text{
		margin-left: 10px;
		margin-top: 10px;
		font-size: 16px;
		font-weight: bold;
	}
	.filterType{
		display: flex;
		flex-direction: row;
		margin-left: 10px;
	}
}
.goodsSearch{
	display: flex;
	flex-direction: column;
}
.typeTitle{
	font-size: 25px;
	font-weight: bold;
	text-align: center;
	margin-top: 10px;
}
.goodsTotal{
	display: flex;
	flex-direction: column;
	width: 100%;
	margin-top: 10px;
	background-color: antiquewhite;
	border-top-left-radius: 15px;
	border-top-right-radius: 15px;
	.filter{
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 10px 50px 10px 50px;
	}
	.goodsList{
		width: 100%;
		margin-left: 10px;
		display: flex;
		flex-direction: row;
		margin-bottom: 15px;
		image{
			width: 130px;
			height: 130px;
			border-radius: 15px;
		}
		.goodsInfor{
			display: flex;
			flex-direction: column;
			.uni-tag{
				margin-right: 5px;
			}
			.inforList{
				margin-left: 10px;
				margin-bottom: 5px;
			}
			.price{
				font-weight: bold;
				font-size: 20px;
				color: #b93a26;
				margin-left: 100px;
			}
		}
	}
}
</style>
