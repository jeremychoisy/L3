import React from 'react'
import {View,Text,Image, StyleSheet} from 'react-native'
import {getSumIcon} from '../API/RIOTApi'

class Summoner extends React.Component{
  render(){
    const {summoner}=this.props
    return(
      <View style={styles.main_container}>
        <View style={styles.summoner}>
          <Image
            style={styles.image}
            source={{uri: getSumIcon(summoner.profileIconId)}}
          />
          <View style={styles.sum_info}>
            <Text style={styles.name}> {summoner.summonerName} </Text>
            <Text style={styles.lvl}>  38 </Text>
            <Text style={styles.league}> GOLD </Text>
          </View>
        </View>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  main_container:{
    height:100,
    flex:1,
    borderWidth:1,
    borderColor:'black'
  },
  summoner:{
    flex:1,
    flexDirection:'row'
  },
  image:{
    width:90,
    height:100,
  },
  sum_info:{
    flex:1,
    flexDirection:'column'
  },
  name:{
    flex:2,
    fontWeight:'bold'
  },
  lvl:{
    flex:2,
    fontWeight:'bold'
  },
  league:{
    flex:1
  }
})

export default Summoner
