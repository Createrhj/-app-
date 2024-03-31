<template>
	<view class="goodsSearch">
		<uni-search-bar v-model="searchMap.heritageName" radius="100" placeholder="其输入搜索内容" clearButton="none" @click="allHeritage"/>
		<view class="goodsTotal">
			<view class="filter" style="font-size: 16px;margin-top: -15px;margin-bottom: 10px;">
				<text style="color: #631216;font-weight: bold;">综合</text>
				<text style="color: #631216;font-weight: bold;">收藏</text>
				<text style="color: #631216;font-weight: bold;">热度</text>
				<text @click="openPopup" style="color: #631216;font-weight: bold;">筛选</text>
			</view>
			<view class="goodsList" v-for="heritage in heritageData" @click="toHeritageDetail(heritage.heritageID)" :key="heritage.heritageID">
				<image :src="imgPrefix+heritage.heritageImg" mode="aspectFill"></image>
				<view class="goodsInfor">
					<view class="inforList" style="margin-top: 2px;font-weight: bold;">
						<text>{{heritage.heritageName}}</text>
					</view>
					<view class="inforList">
						<text>{{heritage.heritageAddress}}</text>
					</view>
					<view class="inforList">
						<uni-tag
						 style="background-color: #b93a26;border-color:#b93a26; color: #fff;"
						 :inverted="true" text="非物资文化遗产" type="error" size="small"/>
						<uni-tag
						 style="background-color: #b93a26;border-color:#b93a26; color: #fff;"
						 :inverted="true" :text="heritage.heritageCategory" type="warning" size="small"/>
					</view>
					<view class="inforList">
						<text>{{heritage.applicationTime}}</text>
					</view>
				</view>
			</view>

		</view>
		
		<uni-popup ref="popup" type="top">
			<view class="filterPopup">
				<text>所属市州</text>
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
				<text style="margin-top: 20px;">申遗时间</text>
				<view class="filterType">
					<input placeholder="最早时间"/>
					<uni-icons type="arrow-right" size="20"></uni-icons>
					<input placeholder="最晚时间"/>
				</view>
				<text>非遗类型</text>
				<view class="filterType">
					<button>民间舞蹈</button>
					<button>民间音乐</button>
					<button>传统戏剧</button>
				</view>
				<view class="filterType">
					<button>手工技艺</button>
					<button>民间美术</button>
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
import HeritageApi from '../../api/HeritageApi'
	export default {
		data() {
			return {
				heritageData:[],
				searchMap:{
					heritageName:'',
					heritageCategory:'',
					heritageCity:'',
					earliestTime:'',
					latestTime:''
				},
				imgPrefix:'http://localhost:9527/app/heritageImg/'
			}
		},
		methods: {
			openPopup(){
			this.$refs.popup.open()
			},
			allHeritage(){
				HeritageApi.allHeritage(this.searchMap).then(res=>res.data).then(res=>{
					this.heritageData=res.data
					for (const key in this.heritageData){
						const imgList=this.heritageData[key].heritageImg.split('/')
						this.heritageData[key].heritageImg=imgList[0]
					}
				})
			},
			toHeritageDetail(heritageID){
				uni.navigateTo({
					url:'/pages/heritageDetail/heritageDetail?heritageID='+heritageID
				})
			}
		},
		mounted(){
			this.allHeritage()
		},
		onLoad(e){
			this.searchMap.heritageCategory=e.heritageCategory
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
