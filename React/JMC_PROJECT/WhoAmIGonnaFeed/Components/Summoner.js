import React from 'react'
import {View,Text,Image, StyleSheet, ActivityIndicator} from 'react-native'
import {getSumIcon,getSum,getLeague,getChampIcon} from '../API/RIOTApi'
import champs from '../Helpers/ChampsData'

class Summoner extends React.Component{
  constructor(props){
    super(props)
    this.state = {
      sumData:undefined,
      leagueData: undefined,
      isLoading:true
    }
  }


  componentDidMount(){
    getSum(this.props.summoner.summonerName).then((sumData)=>{
      getLeague(this.props.summoner.summonerId).then((data)=>{
        this.setState({
          sumData:sumData,
          leagueData:data,
          isLoading:false
        })
      })
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

  _displayLeagueIcon(){
    if(this.state.leagueData !== undefined && this.state.leagueData.lenght !== 0){
    switch(this.state.leagueData[0].tier){
      case 'BRONZE':
        return <Image
              source={require('../Images/leagues_icons/bronze.png')}
              style={styles.icon}
            />
      case 'SILVER':
        return <Image
                source={require('../Images/leagues_icons/silver.png')}
                style={styles.icon}
              />
      case 'GOLD':
        return <Image
              source={require('../Images/leagues_icons/gold.png')}
              style={styles.icon}
            />
      case 'PLATINUM':
        return <Image
              source={require('../Images/leagues_icons/platinum.png')}
              style={styles.icon}
            />
      case 'DIAMOND' :
        return <Image
            source={require('../Images/leagues_icons/diamond.png')}
            style={styles.icon}
          />
      case 'CHALLENGER' :
        return <Image
            source={require('../Images/leagues_icons/challenger.png')}
            style={styles.icon}
          />
      case 'MASTER':
        return <Image
            source={require('../Images/leagues_icons/master.png')}
            style={styles.icon}
          />
      default :
        return <Image
            source={require('../Images/leagues_icons/provisional.png')}
            style={styles.icon}
          />
    }
  }
  }

  _displaySummoner(){

    const {leagueData,sumData} = this.state
    const champIndex = champs.findIndex(item => item.id == this.props.summoner.championId)

    if((leagueData !== undefined && leagueData.length !== 0) && sumData !== undefined){
      return(
        <View style={styles.scrollview_container}>
          <View style={styles.summoner}>
            <Image
              style={styles.image}
              source={{uri: getSumIcon(this.state.sumData.profileIconId)}}
            />
            <View style={styles.sum_info}>
              <Text style={styles.name}> {this.state.sumData.name} </Text>
              <Text style={styles.lvl}>  Lvl : {this.state.sumData.summonerLevel} </Text>
              {this._displayLeagueIcon()}
              <Text style={styles.league}> {this.state.leagueData[0].tier} {this.state.leagueData[0].rank} </Text>
              <Text style={styles.ratio}>  {this.state.leagueData[0].wins} W / {this.state.leagueData[0].losses} L </Text>
            </View>
            <View style={styles.champ_info}>
              <Image
                style={styles.image_champ}
                source={{uri: getChampIcon(champs[champIndex].name)}}
              />
            </View>
          </View>
        </View>
      )
    }
    else if(leagueData !== undefined && sumData !== undefined)
    {
      return(
        <View style={styles.scrollview_container}>
          <View style={styles.summoner}>
            <Image
              style={styles.image}
              source={{uri: getSumIcon(this.state.sumData.profileIconId)}}
            />
            <View style={styles.sum_info}>
              <Text style={styles.name}> {this.state.sumData.name} </Text>
              <Text style={styles.lvl}>  Lvl : {this.state.sumData.summonerLevel} </Text>
              <Image
                 source={require('../Images/leagues_icons/provisional.png')}
                 style={styles.icon}
               />
              <Text style={styles.league}> UNRANKED</Text>
              <Text style={styles.ratio}> 0 W / 0 L</Text>
            </View>
            <View style={styles.champ_info}>
            <Image
              style={styles.image_champ}
              source={{uri: getChampIcon(champs[champIndex].name)}}
            />
            </View>
          </View>
        </View>
      )
    }
  }


  render(){
    return(
      <View style={styles.main_container}>
        {this._displayLoading()}
        {this._displaySummoner()}
      </View>
    )
  }
}

const styles = StyleSheet.create({
  main_container:{
    flex:1
  },
  scrollview_container:{
    height:90,
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
    height:100
  },
  sum_info:{
    flex:2,
    flexDirection:'column',
    alignItems:'center'
  },
  champ_info:{
    flex:1,
    alignItems:'flex-end'
  },
  image_champ:{
    width:45,
    height:50
  },
  name:{
    flex:1,
    fontWeight:'bold'
  },
  lvl:{
    flex:1,
    fontWeight:'bold',

  },
  league:{
    flex:1
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
  icon:{
    width:30,
    height:20
  }
})

export default Summoner
