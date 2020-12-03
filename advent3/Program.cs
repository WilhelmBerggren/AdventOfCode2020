using System;
using System.IO;
using System.Linq;

var data = File.ReadAllText(Environment.CurrentDirectory + "/../input3.txt");

var rows = data
    .Split(Environment.NewLine)
    .Where(s => s.Trim() != "")
    .Select(row => row.Split(""))
    .ToArray();

var width = rows[1].Length;
var dx = 1;
var dy = 1;
var x = 0;
var y = 0;
for (var i = 0; i < rows.Length; i++) {
    x = i * dx;
    y = i * dy;
    Console.WriteLine(String.Join("", rows[i]) + $"\t x: {x % width} width: {width}");
}

Console.WriteLine(data.Split(Environment.NewLine)[0].Split(""));