import axios from "axios";

const Api_URL = 'http://localhost:8081/api/food';
export const addFood = async(foodData,image) =>{
const formData = new FormData();
        formData.append('food', JSON.stringify(foodData));
        formData.append('file',image);

        try{
          await axios.post(Api_URL ,formData, {headers: {"Content-Type": "multipart/form-data"}});
          
        }catch (error){
            console.log('Error',error);
              throw error;
        }  
}
export const getFoodList = async ()=>{
  try{
    const response = await axios.get(Api_URL);
     return response.data;
     
  }catch (error){
   console.log('error fatching food list', error);
   throw error;
  }

}
export const deleteFood = async (foodId)=>{
  try{
      const response = await axios.delete(Api_URL+"/"+foodId);
       return response.data.status === 204;
     }catch (error){
      console.log('Error while deleting the food.', error);
       throw error;
  }
}