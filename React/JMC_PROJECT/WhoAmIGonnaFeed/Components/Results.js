import React from 'react'
import {ScrollView, View,Text,StyleSheet,Image,ActivityIndicator,FlatList} from 'react-native'
import SummonerItem from './SummonerItem'
import game from '../Data/gameData'
import {Font} from 'expo'

class Results extends React.Component{
  constructor(props){
    super(props)
    this.gameType = ""
    this.state={
      summoners : [],
      isLoading: true,
      isFontLoaded:false
    }
  }
  async componentDidMount(){
    await Font.loadAsync({
      'FrizQuadrata' : require('../assets/fonts/FrizQuadrata.ttf')
    })
    const {gameData} = this.props.navigation.state.params

    this.gameType = game[game.findIndex(item => item.id == gameData.gameQueueConfigId)].type;

    this.setState({
      summoners : gameData.participants,
      isLoading:false,
      isFontLoaded:true
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
  render(){
    return(
      this.state.isFontLoaded ?(
      <ScrollView style={styles.main_container}>
        {this._displayLoading()}
        <Text style={styles.text_header}>{this.gameType}</Text>
        <View style={styles.second_container}>
          <View style={styles.team_one}>
            <FlatList
              data={this.state.summoners.filter((sum)=> sum.teamId == 100)}
              keyExtractor={(item) => item.summonerId.toString()}
              renderItem={({item}) => <SummonerItem summoner={item}/>}
            />
          </View>
          <View style={styles.versus}>
            <Text style={styles.text_versus}> VS </Text>
          </View>
          <View style={styles.team_two}>
          <FlatList
            data={this.state.summoners.filter((sum)=> sum.teamId == 200)}
            keyExtractor={(item) => item.summonerId.toString()}
            renderItem={({item}) => <SummonerItem summoner={item}/>}
          />
          </View>
        </View>
      </ScrollView>) : null
    )
  }
}

const styles=StyleSheet.create({
  main_container:{
    flex:1,
    backgroundColor:'black'
  },
  second_container:{
    flexDirection:'row'
  },
  team_one:{
    flex:3,
    backgroundColor:'blue',

  },
  versus:{
    flex:1,
    borderColor:'black',
    borderWidth: 1,
    alignItems:'center',
    justifyContent:'center'
  },
  text_versus:{
    fontSize:35,
    color:'yellow'
  },
  team_two:{
    flex:3,
    backgroundColor:'red',
  },
  text_header:{
    fontFamily: 'FrizQuadrata',
    textAlign:"center",
    fontSize:16,
    fontWeight:'bold',
    color:'yellow'
  },
  image:{
    width:50,
    height:50
  },
  loading_container: {
    position: 'absolute',
    left: 0,
    right: 0,
    top: 0,
    bottom: 0,
    alignItems: 'center',
    justifyContent: 'center'
  }
})

export default Results
