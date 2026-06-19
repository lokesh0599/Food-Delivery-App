import React from 'react';
import Menubar from './components/menubar/menubar';
import { Route, Routes } from 'react-router-dom';
import Home from './pages/Home/Home';
import ContactUs from './pages/Contact Us/ContactUs';
import ExploreFood from './pages/Explore/ExploreFood';
import FoodDetails from './pages/FoodDetails/FoodDetails'
import Cart from './pages/cart/cart';
import PlaceOrder from './pages/PlaceOrder/PlaceOrder';
import Login from './components/Login/Login';
import Register from './components/Register/Register';


const App = () => {
  return (
    <div>
      <Menubar />
      <Routes>
        <Route path='/' element={<Home />}/>
        <Route path='/contact' element={<ContactUs />}/>
        <Route path='/explore' element={<ExploreFood />}/>
        <Route path='/food/:id' element={<FoodDetails />}/>
        <Route path='/carts' element={<Cart />}/>
        <Route path='/order' element={<PlaceOrder />}/>
        <Route path='/login' element={<Login />}/>
        <Route path='/register' element={<Register />}/>
      </Routes>
    </div>
  )
}

export default App
