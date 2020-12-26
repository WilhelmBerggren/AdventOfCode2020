using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

var data = File
    .ReadAllText(Environment.CurrentDirectory + "/../input8.txt")
    .Split("\n")
    .ToArray();

var instrcnt = Enumerable.Range(0, data.Length).Select(i => 0).ToArray();

var count = 0;
var acc = 0;
var index = 0;
var finished = false;
while (!finished) {
    count++;
    var item = data[index].Split(" ");
    // if (index == 625) item = new[] {"nop", "+0"};
    var instr = item[0];
    var num = Int32.Parse(item[1]);
    var index_inc = 1;
    System.Console.WriteLine($"count: {count} index: {index} instr: {data[index]} acc: {acc}");
    if (instr == "acc") acc += num;
    if (instr == "jmp") index_inc = num;
    index += index_inc;
    instrcnt[index]++;
    if (instrcnt[index] > 50) {
        System.Console.WriteLine($"index: {index}, instr: {data[index]}");
        finished = true;
    }
    if(index >= data.Length) finished = true;
}

System.Console.WriteLine(acc);

var cnts = Enumerable
    .Range(0, data.Count())
    .Select(i => (data[i], instrcnt[i], i))
    .Where(t => t.Item2 > 49);

foreach (var item in cnts)
{
    System.Console.WriteLine(item);
}

// for (int i = 0; i < data.Length; i++)
// {
//     System.Console.WriteLine($"instr: {data[i]} index: {i} count: {instrcnt[i]}");
// }