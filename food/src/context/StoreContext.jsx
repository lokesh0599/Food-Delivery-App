import axios from "axios";
import { createContext, useEffect, useState } from "react";

export const StoreContext =createContext(null);

export const StoreContextProvider =(props) =>{

     const [foodList,setFoodList] = useState([])
     const [quantities, setQuantities] =useState({})
     const increaseQty =(foodId) =>{
        setQuantities((prev) =>({...prev, [foodId]: (prev[foodId] || 0)+1}))
     }
   const decreaseQty =(foodId) =>{
    setQuantities((prev)=>({...prev, [foodId] : prev[foodId] > 0 ? prev[foodId] - 1:0}))
   }
   const removeFromCart =(foodId) =>{
    setQuantities((prevQuntities)=> {
        const upadatedQuantities ={...prevQuntities}
        delete upadatedQuantities[foodId]
        return upadatedQuantities

    })
   }
   
    
    const contextValue ={
     foodList,
     increaseQty,
     decreaseQty,
     quantities,
     removeFromCart
    }
    const fetchFoodList = async () =>{
        const response = await axios.get('http://localhost:8081/api/food');
        setFoodList(response.data)
        console.log(response.data)
    }

    useEffect(()=>{
       async function loadData(){
          await fetchFoodList()
        }
        loadData()
    },[])
    return(
        <StoreContext.Provider value={contextValue}>
            {props.children}
        </StoreContext.Provider>
    )
}
    
