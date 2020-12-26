using System;
using System.IO;
using System.Linq;

var data = File.ReadAllText(Environment.CurrentDirectory + "/../../inputs/input2.txt");

var limits = 
    from line in data.Split(Environment.NewLine)
    let parts = line.Split(" ")
    let minmax = parts[0].Split("-")
    select new {
        Min = Int32.Parse(minmax[0]),
        Max = Int32.Parse(minmax[1]),
        Letter = parts[1].Split(":")[0][0],
        Password = parts[2],
    };

var part1 = 
    from l in limits
    let amount = l.Password.Count(c => c == l.Letter)
    select amount >= l.Min && amount <= l.Max;

var part2 = 
    from l in limits
    let hit1 = l.Password[l.Max - 1] == l.Letter
    let hit2 = l.Password[l.Min - 1] == l.Letter
    select hit1 ^ hit2;

System.Console.WriteLine(part1.Where(a => a).Count());
System.Console.WriteLine(part2.Where(a => a).Count());