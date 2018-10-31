import React from 'react'
import {View,Text,StyleSheet} from 'react-native'

class MyAccount extends React.Component{
  render(){
    return(
      <View>
        <Text style={styles.text}> MY ACCOUNT </Text>
      </View>
    )
  }
}
const styles = StyleSheet.create({
  text:{
    marginTop:20
  }
})
export default MyAccount
