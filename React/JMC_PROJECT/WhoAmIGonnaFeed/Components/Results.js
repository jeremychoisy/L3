import React from 'react'
import {ScrollView, View,Text,StyleSheet,Image} from 'react-native'
import {getSum, getSumIcon} from '../API/RIOTApi'

class Results extends React.Component{

constructor(props){
  super(props)

  this.state={
    sumLvl : "",
    sumId : "",
    profileIconId: ""
  }
}
componentDidMount(){
  getSum(this.props.navigation.state.params.nomSum).then(data => {
    this.setState({
      sumLvl : data.summonerLevel,
      sumId : data.id,
      profileIconId : data.profileIconId
    })
  })
}
  render(){
    return(
      <ScrollView>
        <Image style={styles.image} source={{uri:getSumIcon(this.state.profileIconId)}}/>
        <Text>SummonerLevel : {this.state.sumLvl} </Text>
      </ScrollView>
    )
  }
}

const styles=StyleSheet.create({
  image:{
    width:50,
    height:50
  }
})

export default Results
