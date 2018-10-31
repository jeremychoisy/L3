import React from 'react'
import {View, Button, TextInput, Text, StyleSheet, ActivityIndicator,} from 'react-native'


class Search extends React.Component{
  constructor(props){
    super(props)
    this.searched_text=""
  }

  _searchTextInputChanged(text){
    this.searched_text=text
  }

  _displayResults = (nomSum) =>{
    this.props.navigation.navigate("Results",{nomSum : nomSum})
  }

  render(){
    return(
      <View style={styles.main_container}>
        <Text style={styles.header_text}> Who am I gonna Feed ? </Text>
        <Text style={styles.text}> Entrez votre nom d'invocateur : </Text>
        <TextInput
            style={styles.textInput}
            placeholder="Nom d'invocateur"
            onChangeText={(text)=>this._searchTextInputChanged(text)}
            onSubmitEditing={()=>this._displayResults(this.searched_text)}/>
        <Button style={styles.button} title='Go !'onPress={()=>this._displayResults(this.searched_text)}/>
      </View>
    )
  }
}



const styles = StyleSheet.create({
  main_container:{
    flex:1,
    marginTop:20,
  },
  header_text:{
    textAlign:'center',
    fontSize:20,
    marginBottom:10
  },
  text:{
    marginBottom:10
  },
  input_text:{
    marginBottom:30
  },
  button:{
  }
})

export default Search
