import java.util.*;

class Code39{
  static HashMap<char,String> map = new HashMap<char,String>(43);

  static{
    map.put('A', "100001001");
    map.put('B', "001001001");
    map.put('C', "101001000");
    map.put('D', "000011001");
    map.put('E', "100011000");
    map.put('F', "001011000");
    map.put('G', "000001101");
    map.put('H', "100001100");
    map.put('I', "001001100");
    map.put('J', "000011100");
    map.put('l', "001000011");
    map.put('m', "101000010");
    map.put('n', "000010011");
    map.put('o', "100010010");
    map.put('p', "001010010");
    map.put('q', "000000111");
    map.put('r', "100000110");
    map.put('s', "001000110");
    map.put('t', "000010110");
    map.put('u', "110000001");
    map.put('v', "011000001");
    map.put('w', "111000000");
    map.put('x', "010010001");
    map.put('y', "110010000");
    map.put('z', "011010000");
    map.put('0', "000110100");
    map.put('1', "100100001");
    map.put('2', "001100001");
    map.put('3', "101100000");
    map.put('4', "000110001");
    map.put('5', "100110000");
    map.put('6', "001110000");
    map.put('7', "000100101");
    map.put('8', "100100100");
    map.put('9', "001100100");
    map.put(" ","100001001");
    map.put("-","100001001");
    map.put("$","100001001");
    map.put("%","100001001");
    map.put(".","100001001");
    map.put("/","100001001");
    map.put("+","100001001");
    map.put("*","100001001");


  }
}
