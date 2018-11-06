import React from 'react'
import {TouchableOpacity, Text, StyleSheet, View, Image, Alert} from 'react-native'
import {getSum, getCurrentMatch} from '../API/RIOTApi'
import {connect} from 'react-redux'

class SearchHistoryItem extends React.Component{

  _displayResults = (nomSum) =>{
    getSum(nomSum).then((sumData)=>{
      getCurrentMatch(sumData.id).then((gameData)=>{
        if(gameData.participants === undefined){
          Alert.alert("This summoner is not currently in game.")
        }
        else
        {
          this.props.goToResults(gameData)
        }
      })
    })
  }

  _removeItem(nomSum){
      const action = { type: "REMOVE_ITEM", value: nomSum }
      this.props.dispatch(action)
  }


  render(){
    return(
      <View style={styles.mainContainer}>
        <TouchableOpacity style={styles.viewSummonerName} onPress={()=>this._displayResults(this.props.sumName)}>
          <Text style={styles.textSummonerName}> {this.props.sumName}</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.deleteSummonerName} onPress={()=>this._removeItem(this.props.sumName)}>
          <Image
          style={styles.icon}
          source={require('../Images/ic_remove.png')}
          />
        </TouchableOpacity>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  mainContainer:{
    height:30,
    flex:1,
    flexDirection:'row'
  },
  viewSummonerName:{
    flex:1,
    borderWidth:1,
    borderColor:'black',
    justifyContent:'center',
  },
  textSummonerName:{
    fontSize:15,
    textAlign:'center',
    fontWeight:'bold'
  },
  deleteSummonerName:{
    width:30
  },
  icon:{
    height:30,
    width:30
  }
})

const mapStateToProps = state =>{
  return{
    searchHistory : state.searchHistory
  }
}

export default connect(mapStateToProps)(SearchHistoryItem)
