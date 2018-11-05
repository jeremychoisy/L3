import React from 'react';
import { TextInput, View, Button } from 'react-native';

export default class App extends React.Component {
  render() {
    return (
      <View style={{ marginTop:20 }}>
        <TextInput placeholder="Titre du film"/>
        <Button title="test" onPress={()=>{}}/>
      </View>
    );
  }
}
