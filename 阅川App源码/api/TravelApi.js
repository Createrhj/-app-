import axios from "axios";
axios.defaults.baseURL='http://localhost:9527/app'
class TravelApi{
	//查看酒店详情功能
	hotelDetail(hotelID){
		return axios.get('/travel/hotelDetail?hotelID='+hotelID)
	}
	
	//查询符合条件的所有酒店
    allHotel(searchMap){
		return axios.post('/travel/allHotel',searchMap)
	}
	
	//查看景点详情功能
	sceneryDetail(sceneryID){
		return axios.get('/travel/sceneryDetail?sceneryID='+sceneryID)
	}
	
	//查询符合条件的所有景点
	allScenery(searchMap){
		return axios.post('/travel/allScenery',searchMap)
	}
	
	//查看剧场详情功能
	theaterDetail(theaterID){
		return axios.get('/travel/theaterDetail?theaterID='+theaterID)
	}
	//查询符合条件的所有剧场
	allTheater(searchMap){
		return axios.post('/travel/allTheater',searchMap)
	}
	
	//查看博物馆详情功能
	museumDetail(museumID){
		return axios.get('/travel/museumDetail?museumID='+museumID)
	}
	//查询符合条件的所有博物馆
	allMuseum(searchMap){
		return axios.post('/travel/allMuseum',searchMap)
	}
}
export default new TravelApi()
