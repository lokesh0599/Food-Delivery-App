import React, { useState } from 'react'
import {assets} from '../../assets/assets';
import axios from 'axios';
import { addFood } from '../../services/foodService';
import { toast } from 'react-toastify';
const AddFood = () => {
    const [image, setImage] = useState(false);
    const [data , setData] = useState({
        name:'',
        description:'',
        price:'',
        category:'Biryani'
     });
     const onChangeHandler = (event)=> {
        const name = event.target.name;
        const value= event.target.value;
        setData(data =>({...data, [name]: value}));
     }
     const onSubmitHandler = async (event)=>{
        event.preventDefault();
        if(!image){
            toast.error('please select an image.')
            return;
        }

       try{
        await addFood(data,image);
        toast.success('Food added successfully.');
        setData({name:'',description:'',category:'Biryani',price:''});
        setImage(null);
       }catch(error){
         toast.error('error adding food.');
       }
     }
     
  return (
    <div className="mt-2">
        <div classNameName="row ">
            <div className="card col-md-4">
                <div className="card shadow-lg">
                    <div className="card-body">
                        <h3 className="card-title text-center mb-4">Add Food </h3>
                        <form onSubmit={onSubmitHandler}>
                            <div className="mb-2">
                                <label htmlFor="image" className="form-label">
                                    <img src={image ? URL.createObjectURL(image): assets.upload} alt='' width={98}/>
                                    </label>
                                <input 
                                    type="file" 
                                    className="form-control"
                                    id='image'
                                    required hidden 
                                    onChange={(e) => setImage(e.target.files[0])} 
                                />
                            </div>
                            <div className="mb-2">
                             <label htmlFor="name" className="form-label">name</label>
                             <input type='text' name='name' id='name' className='form-control' required name='name'
                             onChange={onChangeHandler} value={data.name}/>
                             </div>
                      
                            <div className="mb-2">
                                <label htmlFor="message" className="form-label">Description</label>
                                <textarea 
                                    className="form-control" 
                                    id="discription" 
                                    rows="4" 
                                    required name='description'
                                    onChange={onChangeHandler} value={data.description}
                                ></textarea>
                            </div>
                             <div className="mb-2">
                                <label htmlFor="category" className="form-label">Category</label>
                                <select 
                                    className="form-control" 
                                    id="category"
                                    required name='category'
                                    onChange={onChangeHandler} 
                                    value={data.category}
                                    
                                >
                                    <option value="Biryani">Biryani</option>
                                    <option value="Cake">Cake</option>
                                    <option value="Burger">Burger</option>
                                    <option value="Pizza">Pizza</option>
                                    <option value="Rolls">Rolls</option>
                                    <option value="Salad">Salad</option>
                                    <option value="Ice cream">Ice cream</option>
                                </select>
                            </div>
                            <div className="mb-2">
                             <label htmlFor="category" className="form-label">price</label>
                             <input type='number' name='price' id='price' className='form-control' onChange={onChangeHandler} value={data.price}/>
                             </div>
                            <div className="text-center">
                                <button type="submit" className="btn btn-primary btn-lg">save</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default AddFood;