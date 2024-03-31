import axios from "axios";
axios.defaults.baseURL='http://localhost:9527/app'
class PsersonalApi{
	collection(userID){
		return axios.get('/personal/collection?userID='+userID)
	}
	
	isCollection(userID,heritageID){
		return axios.get('/personal/isCollection?userID='+userID+'&heritageID='+heritageID)
	}

    addCollection(userID,heritageID){
		return axios.get('/personal/addCollection?userID='+userID+'&heritageID='+heritageID)
	}
	delCollection(userID,heritageID){
		return axios.get('/personal/delCollection?userID='+userID+'&heritageID='+heritageID)
	}
	updateUser(user){
		return axios.post('/personal/update',user)
	}
}
export default new PsersonalApi()
