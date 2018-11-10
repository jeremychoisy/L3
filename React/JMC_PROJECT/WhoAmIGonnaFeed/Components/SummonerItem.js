import React from 'react'
import {View,Text,Image, StyleSheet, ActivityIndicator} from 'react-native'
import {getSumIcon,getSum,getLeague,getChampIcon,getRuneIcon, getRunes} from '../API/RIOTApi'
import champs from '../Data/ChampsData'

class SummonerItem extends React.Component{
  constructor(props){
    super(props)
    getRunes().then(data => this.runes = data)
    this.state = {
      sumData:undefined,
      leagueData: undefined,
      isLoading:true,
      dataLoaded:false
    }
  }


  componentDidMount(){
    getSum(this.props.summoner.summonerName).then((sumData)=>{
      getLeague(this.props.summoner.summonerId).then((data)=>{
        this.setState({
          sumData:sumData,
          leagueData:data,
          isLoading:false,
          dataLoaded:true
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

  _displayChampAndRunes(){
    if(this.runes !== undefined){
      const champIndex = champs.findIndex(item => item.id == this.props.summoner.championId)
      const mainRuneIndex = this.runes.findIndex(item => item.id == this.props.summoner.perks.perkStyle)
      const mainTalentIndex = this.runes[mainRuneIndex].slots[0].runes.findIndex(item => item.id == this.props.summoner.perks.perkIds[0])
      const secondaryRuneIndex = this.runes.findIndex(item => item.id == this.props.summoner.perks.perkSubStyle)

      return(
        <View style={styles.champAndRunes}>
          <View style={styles.champ}>
            <Image
              style={styles.image_champ}
              source={{uri: getChampIcon(champs[champIndex].name)}}
            />
          </View>
          <View style={styles.mainRune}>
            <Image
              style={styles.iconMainRune}
              source={{uri: getRuneIcon(this.runes[mainRuneIndex].slots[0].runes[mainTalentIndex].icon)}}
            />
          </View>
          <View style={styles.secondaryRune}>
            <Image
                style={styles.iconSecondaryRune}
                source={{uri: getRuneIcon(this.runes[secondaryRuneIndex].icon)}}
            />
          </View>
        </View>
      )
    }
  }

  _displayRank(){
    if(this.state.leagueData !== undefined){
      if(this.state.leagueData.length !== 0 ){
        return(
          <View style={styles.sum_rank}>
            <Text style={styles.league}> {this.state.leagueData[0].tier} {this.state.leagueData[0].rank} </Text>
            {this._displayLeagueIcon()}
            <Text style={styles.ratio}>  {this.state.leagueData[0].wins} W / {this.state.leagueData[0].losses} L </Text>
          </View>
        )
      }
      else{
        return(
          <View style={styles.sum_rank}>
            {this._displayLeagueIcon()}
            <Text style={styles.league}> UNRANKED </Text>
            <Text style={styles.ratio}> 0 W / 0 L</Text>
          </View>
        )
      }
    }
  }

  _displayLeagueIcon(){
    const {leagueData} = this.state
    if(leagueData !== undefined){
      if(leagueData.length !== 0){
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
      else {
        return(
          <Image
            source={require('../Images/leagues_icons/provisional.png')}
            style={styles.icon}
          />
        )
      }
    }
  }

  _displaySummoner(){

    const {sumData} = this.state
    const champIndex = champs.findIndex(item => item.id == this.props.summoner.championId)

    if(this.state.dataLoaded){
      return(
        <View style={styles.scrollview_container}>
          <View style={styles.summoner}>
            <Image
              style={styles.image}
              source={{uri: getSumIcon(this.state.sumData.profileIconId)}}
            />
            <View style={styles.sum_info}>
              <View style={styles.sum_details}>
                <Text style={styles.name} numberOfLines={1}> {this.state.sumData.name} </Text>
                <Text style={styles.lvl}>  Lvl : {this.state.sumData.summonerLevel} </Text>
              </View>
              {this._displayRank()}
            </View>
              {this._displayChampAndRunes()}
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
    height:110,
    flex:1,
    borderWidth:1,
    borderColor:'black'
  },
  summoner:{
    flex:1,
    flexDirection:'row'
  },
  image:{
    width:100,
    height:110
  },
  // Summoner infos
  sum_info:{
    flex:2,
    flexDirection:'column',
    alignItems:'center'
  },
  sum_details:{
    alignItems:'center',
    flex:1,
    marginBottom:10
  },
  // Summoner rank
  sum_rank:{
    alignItems:'center',
    flex:2
  },
  league:{
    flex:1
  },
  ratio:{
    flex:1,
    fontSize:10
  },
  // Champ
  champAndRunes:{
    flex:1,
  },
  champ:{
    alignItems:'flex-end',
    marginBottom: 10
  },
  mainRune:{
    alignItems: 'center'
  },
  secondaryRune:{
    alignItems:'flex-end'
  },
  image_champ:{
    width:45,
    height:50
  },
  iconMainRune:{
    width:25,
    height:25
  },
  iconSecondaryRune:{
    width:10,
    height:10
  },
  name:{
    fontWeight:'bold'
  },
  lvl:{
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

export default SummonerItem
