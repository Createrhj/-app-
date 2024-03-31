<template>
	<view class="goodsSearch">
		<uni-search-bar radius="100" placeholder="其输入搜索内容" clearButton="none" />
		<view class="goodsTotal">
			<view class="filter"style="font-size: 16px;margin-top: -15px;margin-bottom: 10px;">
				<text style="color: #631216;font-weight: bold;">综合</text>
				<text style="color: #631216;font-weight: bold;">价格</text>
				<text style="color: #631216;font-weight: bold;">销量</text>
				<text @click="openPopup" style="color: #631216;font-weight: bold;">筛选</text>
			</view>
			<view class="goodsList" @click="toMuseumDetail(museum.museumID)" v-for="museum in museumData" :key="museum.museumID">
				<image :src="imgPrefix+museum.museumImg" mode="aspectFill"></image>
				<view class="goodsInfor">
					<view class="inforList" style="margin-top: 2px;font-weight: bold;">
						<text>{{museum.museumName}}</text>
					</view>
					<view class="inforList">
						<text>{{museum.museumCity}}</text>
					</view>
					<view class="inforList">
						<uni-tag 
						style="background-color: #cb523e; border-color: #cb523e; color: #fff;"
						:inverted="true" :text="museum.museumEvaluate[0]" type="error" size="small"/>
						<uni-tag 
						style="background-color: #cb523e; border-color: #cb523e; color: #fff;"
						:inverted="true" :text="museum.museumEvaluate[1]" type="error" size="small"/>
					</view>
					<view class="inforList">
						<text>{{museum.museumTime}}</text>
					</view>
				</view>
			</view>

		</view>
		
		<uni-popup ref="popup" type="top">
			<view class="filterPopup">
				<text>城市</text>
				<view class="filterType">
					<button>成都</button>
					<button>宜宾</button>
					<button>绵阳</button>
				</view>
				<view class="filterType">
					<button>凉山</button>
					<button>甘孜</button>
					<button>德阳</button>
				</view>
				<text style="margin-top: 20px;">开馆时间</text>
				<view class="filterType">
					<input placeholder="最早时间"/>
					<uni-icons type="arrow-right" size="20"></uni-icons>
					<input placeholder="最晚时间"/>
				</view>
				<text>馆藏类型</text>
				<view class="filterType">
					<button>非遗馆</button>
					<button>艺术馆</button>
					<button>博物馆</button>
				</view>
				<view class="filterType">
					<button>凉山</button>
					<button>甘孜</button>
					<button>德阳</button>
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
				museumData:[],
				searchMap:{
					museumName:'',
					museumCategory:'',
					museumCity:'',
				},
				imgPrefix:'http://localhost:9527/app/museumImg/'
			}
		},
		methods: {
			openPopup(){
			// 通过组件定义的ref调用uni-popup方法 ,如果传入参数 ，type 属性将失效 ，仅支持 ['top','left','bottom','right','center']
			this.$refs.popup.open()
			},
			allMuseum(){
				TravelApi.allMuseum(this.searchMap).then(res=>res.data).then(res=>{
					this.museumData=res.data
					for (const key in this.museumData){
						const imgList=this.museumData[key].museumImg.split('/')
						const evaluate=this.museumData[key].museumEvaluate.split('、')
						this.museumData[key].museumEvaluate=evaluate
						this.museumData[key].museumImg=imgList[0]
					}
				})
			},
			toMuseumDetail(museumID){
				uni.navigateTo({
					url: '/pages/museumDetail/museumDetail?museumID='+museumID
				})
			}
		},
		mounted(){
			this.allMuseum()
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
	margin-top: 25px;
	width: 150px;
	height: 40px;
	border-radius: 10px;
	font-size: 17px
}
button{
	width: 80px;
	height: 30px;
	margin: 8px 0px 0px 10px;
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
	margin-top: 8px;
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
				margin-bottom: 10px;
			}
			.price{
				font-weight: bold;
				font-size: 20px;
				color: #FFBB57;
				margin-left: 135px;
			}
		}
	}
}
</style>
