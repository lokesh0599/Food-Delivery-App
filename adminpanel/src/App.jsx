import React, { useState } from 'react'
import { Routes, Route } from 'react-router-dom';
import AddFood from './Pages/AddFood/AddFood';
import ListFood from './Pages/ListFood/ListFood';
import Order from './Pages/Orders/Order';
import Sidebar from './components/Sidebar/Sidebar';
import Menubar from './components/Menubar/Menubar';
import { ToastContainer } from 'react-toastify';

const App = () => {
   const[sidebarVisible, setSidebarVisible] = useState(true);
   const toggleSider =() =>{
      setSidebarVisible(!sidebarVisible);
   }
  return (
     <div className="d-flex" id="wrapper">
            <Sidebar sidebarVisible={sidebarVisible}/>

            <div id="page-content-wrapper"> 
               <Menubar toggleSider={toggleSider}/>
                 
                <div className="container-fluid">

                   <Routes>
                     <Route path='/add' element={<AddFood />} />
                     <Route path='/list' element={<ListFood />} />
                     <Route path='order' element={<Order />} />
                     <Route path='/' element={<ListFood />} />
                   </Routes>
                </div>
            </div>
        </div>
  )
}

export default App;