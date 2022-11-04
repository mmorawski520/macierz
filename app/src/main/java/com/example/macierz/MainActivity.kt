package com.example.macierz

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    var i:Int = 0;
    var j:Int = 0;
    var arrSize = 10;
    val array = Array(arrSize, {IntArray(arrSize)});
    var path:String =""

    lateinit var btnApply:Button;
    lateinit var btnFind:Button;
    lateinit var editTextValue:EditText;
    lateinit var editTextFind:EditText;
    lateinit var resultTextView:TextView;
    lateinit var editTextNode:TextView;

    lateinit var editTextI:EditText;
    lateinit var btnIForward:Button;
    lateinit var btnIBack:Button;

    lateinit var editTextJ:EditText;
    lateinit var btnJForward:Button;
    lateinit var btnJBack:Button;
     var startNode:Int = 0;

    private val NO_PARENT = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       generateGraph();

        btnApply = findViewById(R.id.btnApply);
        btnFind = findViewById(R.id.buttonFind);
        resultTextView = findViewById(R.id.textViewResult);

        editTextFind = findViewById(R.id.editTextValueToFind)
        editTextValue = findViewById(R.id.editTextValue);
        editTextNode = findViewById(R.id.editTextTextPersonName);

        editTextI = findViewById(R.id.editTextI);
        btnIForward = findViewById(R.id.btnIForward);
        btnIBack = findViewById(R.id.btnIBack);

        editTextJ = findViewById(R.id.editTextJ);
        btnJForward = findViewById(R.id.btnJForward);
        btnJBack = findViewById(R.id.btnJBack);



        btnIForward.setOnClickListener{
            if(i<arrSize){
                i++;}
            editTextI.setText(i.toString());
            this.printValue();
        }

        btnIBack.setOnClickListener{
            if(i!=0){
                i--;
            }
            editTextI.setText(i.toString());
            this.printValue();
        }

        btnJForward.setOnClickListener {
            if(j<arrSize){
                j++;}
            editTextJ.setText(j.toString());
            this.printValue();
        }

        btnJBack.setOnClickListener {
            if(j!=0){
                j--;
            }
            editTextJ.setText(j.toString());

            this.printValue();
        }

        btnApply.setOnClickListener {
            array[i][j] =editTextValue.text.toString().toInt();
        }

        btnFind.setOnClickListener {
            startNode = editTextNode.text.toString().toInt()
            dijkstra(array,editTextFind.text.toString().toInt())

        }

        editTextValue.setText(  array[i][j].toString())
    }

    private fun dijkstra(
        adjacencyMatrix: Array<IntArray>,
        startVertex: Int
    ) {
        val nVertices = adjacencyMatrix[0].size
        val shortestDistances = IntArray(nVertices)
        val added = BooleanArray(nVertices)

        for (vertexIndex in 0 until nVertices) {
            shortestDistances[vertexIndex] = Int.MAX_VALUE
            added[vertexIndex] = false
        }

        shortestDistances[startVertex] = 0

        val parents = IntArray(nVertices)
        parents[startVertex] = NO_PARENT

        for (i in 1 until nVertices) {
            var nearestVertex = -1
            var shortestDistance = Int.MAX_VALUE
            for (vertexIndex in 0 until nVertices) {
                if (!added[vertexIndex] &&
                    shortestDistances[vertexIndex] <
                    shortestDistance
                ) {
                    nearestVertex = vertexIndex
                    shortestDistance = shortestDistances[vertexIndex]
                }
            }

            added[nearestVertex] = true
            for (vertexIndex in 0 until nVertices) {
                val edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex]
                if (edgeDistance > 0
                    && shortestDistance + edgeDistance <
                    shortestDistances[vertexIndex]
                ) {
                    parents[vertexIndex] = nearestVertex
                    shortestDistances[vertexIndex] = shortestDistance +
                            edgeDistance
                }
            }
        }
        printSolution(startVertex, shortestDistances, parents)
    }

    fun reverseStr(str: String): String{
        if(str.isEmpty())
            return str
        return reverseStr(str.substring(1)) + str[0]
    }

    private fun printSolution(
        startVertex: Int,
        distances: IntArray,
        parents: IntArray
    ) {
        val nVertices = distances.size
        var message = "";
        path = ""

        for (vertexIndex in 0 until nVertices) {
            if (vertexIndex != startVertex && startNode == vertexIndex) {
                message += "\n$vertexIndex to "
                message +="$startVertex \t\t is "
                message += distances[vertexIndex].toString() + "\t\t path "
                printPath(vertexIndex, parents)
                message += reverseStr(path);
            }
        }
        resultTextView.text = message; 4}


    private fun printPath(
        currentVertex: Int,
        parents: IntArray
    ):String {

        if (currentVertex == NO_PARENT) {
            path += ""
            return ""
        }
        printPath(parents[currentVertex], parents)
        path += currentVertex.toString()+ " "
        return "$currentVertex";
    }
    fun printValue(){
        editTextValue.setText((array[i][j]).toString());
    }

    fun generateGraph(){
        for(y in 0..arrSize-1){
            for(x in 0..arrSize-1){
               array[y][x] = Random.nextInt(0,10);
            }
        }}
}