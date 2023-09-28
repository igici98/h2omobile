import React, { Component } from 'react';
import H2oApp from './components/h2omobile/H2oApp.jsx';
import './App.css';
import './bootstrap.css';

class App extends Component {

  constructor() {
    super();//state super, for using "this"
    this.state = {
      counter: 0
    }


  }


  render() {
    return (

      //JSX Code - extansion of java script
      //im Hintergrund wird mit Babel der JSX Code zu Javascript Code compeliert
      <div className="App">



        <H2oApp />


      </div>

    );
  }
}


export default App;