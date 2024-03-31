import axios from "axios";
axios.defaults.baseURL='http://localhost:9527/app'
class HeritageApi{
	//查询非遗详情功能
	heritageDetail(heritageID){
		return axios.get('/heritage/heritageDetail?heritageID='+heritageID)
	}
	//查询符合调节的所有非遗
    allHeritage(searchMap){
		return axios.post('/heritage/allHeritage',searchMap)
	}
	
}
export default new HeritageApi()
