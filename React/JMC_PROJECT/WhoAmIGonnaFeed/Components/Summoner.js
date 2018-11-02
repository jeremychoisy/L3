import React from 'react'
import {View,Text,Image, StyleSheet, ActivityIndicator} from 'react-native'
import {getSumIcon,getSum,getLeague} from '../API/RIOTApi'

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
    getSum(this.props.summoner.summonerName).then((data)=>{
        this.setState({
          sumData:data
        })
    })
    getLeague(this.props.summoner.summonerId).then((data)=>{
      this.setState({
        leagueData:data,
        isLoading:false
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

  _displaySummoner(){
    const {leagueData,sumData} = this.state
    if(leagueData !== undefined && sumData !== undefined){
      return(
        <View style={styles.scrollview_container}>
          <View style={styles.summoner}>
            <Image
              style={styles.image}
              source={{uri: getSumIcon(this.state.sumData.profileIconId)}}
            />
            <View style={styles.sum_info}>
              <Text style={styles.name}> {this.state.sumData.name} </Text>
              <Text style={styles.lvl}>  {this.state.sumData.summonerLevel} </Text>
              {this._displayLeagueIcon()}
              <Text style={styles.league}> {this.state.leagueData[0].tier} {this.state.leagueData[0].rank} </Text>
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
