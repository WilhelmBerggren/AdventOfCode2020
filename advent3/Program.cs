using System;
using System.IO;
using System.Linq;

var data = File.ReadAllText(Environment.CurrentDirectory + "/../input3.txt");

var rows = data
    .Split(Environment.NewLine)
    .Select(row => row.ToCharArray())
    .ToArray();

var width = rows[0].Length;

Func<int, int, int> GetHits = (dx, dy) => {
    var x = 0;
    var y = 0;
    var hits = 0;
    for (int i = 0; i < rows.Length; i++)
    {
        x = i * dx % width;
        y = i * dy;
        if (y > rows.Length) return hits;
        if (rows[y][x] == '#') hits++;
    }
    return hits;
};

var answers = new (int dx, int dy)[] { (1, 1), (3, 1), (5, 1), (7, 1), (1, 2) }
    .Select(pair => GetHits(pair.dx, pair.dy));

var sum = answers.Select(x => (long) x).Aggregate((acc, x) => acc * x);

System.Console.WriteLine(sum);