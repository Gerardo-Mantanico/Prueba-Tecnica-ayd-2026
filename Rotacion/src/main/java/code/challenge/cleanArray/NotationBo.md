# Notacion BigO complegidad

---

### funcion critica
```
    public static void concatArrays(Object[] array1, Object[] array2, int index) {
        for (int i = index; i < array1.length; i++) {
            if (array1[i] instanceof Object[]) {
                Object[] temp = (Object[]) array1[i];
                CleanArray.concatArrays(array1, temp, i);
            }else{
                array2[i] = array1[i];
            }
        }
        //return index;
    }
```
ya que se usa una funcion recursiva el algorimto es N al cuadrado