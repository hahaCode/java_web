// import React from 'react';
// import ReactDOM from 'react-dom';
// import './index.css';
// import App from './App';
// import reportWebVitals from './reportWebVitals';

// ReactDOM.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>,
//   document.getElementById('root')
// );

// // If you want to start measuring performance in your app, pass a function
// // to log results (for example: reportWebVitals(console.log))
// // or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();

// ================================render props 模式 状态逻辑复用=====================================>
// import React from "react";
// import ReactDOM from "react-dom";
// import cat from './imgs/cat.jpg';
// import Prototypes from 'prop-types'

// // Mouse组件负责封装复用的状态逻辑代码
// class Mouse extends React.Component {
//     state = {
//         x: 0,
//         y: 0,
//     };

//     handelMouseMove = e => {
//       this.setState({
//         x: e.clientX,
//         y: e.clientY
//       })
//     }

//     //监听鼠标移动事件
//     componentDidMount() {
//       window.addEventListener('mousemove', this.handelMouseMove)
//     }

//     //组件卸载时移除事件绑定
//     componentWillUmount () {
//       window.removeEventListener('mousemove', this.handelMouseMove)
//     }

//     render() {
//       //return null
//       //return this.props.render(this.state)

//       //推荐使用children属性
//       return this.props.children(this.state)
//     }
// }

// Mouse.propTypes = {
//   children: Prototypes.func.isRequired
// }

// class App extends React.Component {
//     render() {
//         return (
//             <div>
//                 <h1> render props 模式 </h1>
                
//                 {/*
//                   <Mouse render={(mouse) => {
//                   return <p> 鼠标位置: {mouse.x} {mouse.y } </p>
//                 }}/>
//               */}

//                 <Mouse> 
//                 {
//                   mouse => {
//                     return (
//                       <p> 鼠标位置: {mouse.x} {mouse.y } </p>
//                     )
//                   }
//                 }
//                 </Mouse>
//                 {/*
                  
//                   <Mouse render={ mouse => {
//                   return <img src={cat} alt="cat" style= {{
//                     position: 'absolute',
//                     top: mouse.y,
//                     left: mouse.x
//                   }}/>
//                 }}></Mouse>
                
//                 */}

//                 <Mouse>
//                  {
//                   mouse => {
//                     return (
//                       <img src={cat} alt="cat" style={{
//                         position: 'absolute',
//                         top: mouse.y-25,
//                         left: mouse.x-15,
//                         with: 50,
//                         height: 30
//                       }}/>
//                     )
//                   }    
//                  }
//                 </Mouse>
                
//             </div>
//         );
//     }
// }

// ReactDOM.render(<App />, document.getElementById("root"));

// =============================高阶组件==================================>
import React from 'react'
import ReactDOM from 'react-dom'

// 创建高阶组件
function withMouse(WrappedComponent) {
  class Mouse extends React.Component {
      state = {
          x: 0,
          y: 0,
      };

      handleMouseMove = (e) => {
          this.setState({
              x: e.clientX,
              y: e.clientY,
          });
      };

      componentDidMount() {
          window.addEventListener("mousemove", this.handleMouseMove);
      }

      //组件卸载时移除事件绑定
      componentWillUmount() {
          window.removeEventListener("mousemove", this.handelMouseMove);
      }

      render() {
        return <WrappedComponent {...this.state} {...this.props}></WrappedComponent>
      }
  }

  Mouse.displayName = `WithMouse${getDisplayName(WrappedComponent)}`

  return Mouse
}

function getDisplayName(WrappedComponent) {
  return WrappedComponent.displayName || WrappedComponent.name || 'Component'
}

const Position = props => (
  <p>
    鼠标当前位置: {props.x}, {props.y}
  </p>
)

//获取增强后的组件
const MousePosition = withMouse(Position)

class App extends React.Component {
  render() {
    return (
      <div>
        <h1>高阶组件</h1>
        <MousePosition/>
      </div>
    )
  }
}

ReactDOM.render(<App />, document.getElementById("root"));