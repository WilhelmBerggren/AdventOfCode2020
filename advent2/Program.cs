using System;
using System.IO;
using System.Linq;

var data = File.ReadAllText(Environment.CurrentDirectory + "/input2.txt");

var pwds = data.Split(Environment.NewLine).Where(s => s.Trim() != "");

var count = 0;
foreach (var p in pwds) {
    var parts = p.Split(" ");
    var limits = parts[0].Split("-");
    var min = limits[0];
    var max = limits[1];
    var letter = parts[1].Split(":")[0];
    var pwd = parts[2];
}
