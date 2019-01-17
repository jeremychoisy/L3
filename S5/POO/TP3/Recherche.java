import java.util.*;

class Recherche{
  public static int recherche(int[] array, int key){
    for(int i=0;i<array.length;i++)
    {
      if(array[i]==key)
      {
        return i;
      }
    }
    return -1;
  }

  public static int rechercheDico(int[] array,int key, int start, int end){
    int half;
    half = (end+start)/2;

    if(array[half]==key)
    {
      return half;
    }
    else if(array[half]>key)
    {
      return rechercheDico(array,key,start,half-1);
    }
    else if(array[half]<key)
    {
      return rechercheDico(array,key,half+1,end);
    }
    else
    {
    return -1;
    }
  }
}
