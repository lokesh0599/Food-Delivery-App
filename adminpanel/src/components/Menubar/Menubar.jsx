import React from 'react'

const Menubar = ({toggleSider}) => {
  return (
     <nav className="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <div className="container-fluid">
                        <button className="btn btn-primary" id="sidebarToggle" onClick={toggleSider}>
                            <i className='bi bi-list'></i>
                        </button>
                       
                    </div>
                </nav>
  )
}

export default Menubar;