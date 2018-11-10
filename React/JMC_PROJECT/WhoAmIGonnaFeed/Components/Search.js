import React from 'react'
import {View, Button, TextInput, Text, StyleSheet, ActivityIndicator, Alert, Image} from 'react-native'
import {getSum, getCurrentMatch} from '../API/RIOTApi'
import {connect} from 'react-redux'
import {Font} from 'expo'


class Search extends React.Component{
  constructor(props){
    super(props)
    this.searched_text=""
    this.state = {
      isLoading:false,
      fontLoaded:false
    }
  }

  async componentDidMount(){
    await Font.loadAsync({
      'FrizQuadrata' : require('../assets/fonts/FrizQuadrata.ttf')
    })
    this.setState({
      fontLoaded:true
    })
  }

  _displayLoading() {
    if (this.state.isLoading) {
      return (
        <View style={styles.loading_container}>
          <ActivityIndicator size='large' />
        </View>
      )
    }
  }

  _searchTextInputChanged(text){
    this.searched_text=text
  }

  _addSearchHistory(nomSum) {
    const action = { type: "ADD_ITEM", value: nomSum }
    this.props.dispatch(action)
  }

  _displayResults = (nomSum) =>{
    this.setState({
      isLoading:true
    })
    this._addSearchHistory(nomSum)
    getSum(nomSum).then((sumData)=>{
      getCurrentMatch(sumData.id).then((gameData)=>{
        this.setState({
          isLoading:false
        })
        if(gameData.participants === undefined){
          Alert.alert("This summoner is not currently in game.")
        }
        else
        {
          this.props.navigation.navigate("Results",{gameData : gameData})
        }
      })
    })
  }

  render(){
    return(
      this.state.fontLoaded ? (
      <View style={styles.main_container}>
        <Text style={styles.text}> Entrez votre nom d'invocateur : </Text>
        <View style={styles.input_container}>
        <TextInput
            style={styles.textInput}
            placeholder="Summoner name"
            onChangeText={(text)=>this._searchTextInputChanged(text)}
            onSubmitEditing={()=>this._displayResults(this.searched_text)}
            underlineColorAndroid="transparent"/>
        </View>
        <Button style={styles.button} title='Go !'onPress={()=>this._displayResults(this.searched_text)}/>
        <Image
          source={require('../Images/evil.jpg')}
          style={styles.image}
        />
      </View>
    ):null
   )
  }
}



const styles = StyleSheet.create({
  main_container:{
    flex:1,
    backgroundColor:'black'
  },
  text:{
    fontFamily: 'FrizQuadrata',
    fontSize:18,
    marginBottom:10,
    color:'rgb(240,219,77)'
  },
  input_container:{
    backgroundColor:'white',
    marginBottom:20
  },
  input_text:{

  },
  button:{
  },
  loading_container: {
    position: 'absolute',
    left: 0,
    right: 0,
    top: 0,
    bottom: 0,
    alignItems: 'center',
    justifyContent: 'center'
  },
  image:{
    marginTop:20,
    height:110
  }
})

const mapStateToProps = state =>{
  return{
    searchHistory : state.searchHistory
  }
}

export default connect(mapStateToProps)(Search)
