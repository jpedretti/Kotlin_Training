.\packages\NUnit.ConsoleRunner.3.8.0\tools\nunit3-console.exe --labels=All --out=.\KotlinTrainingTestProject\TestResult.txt "--result=.\KotlinTrainingTestProject\TestResult.xml;format=nunit2" .\KotlinTrainingTestProject\bin\Debug\KotlinTrainingTestProject.dll
.\packages\SpecFlow.2.2.1\tools\specflow.exe nunitexecutionreport .\KotlinTrainingTestProject\KotlinTrainingTestProject.csproj /xmlTestResult:.\KotlinTrainingTestProject\TestResult.xml /out:.\KotlinTrainingTestProject\TestResult.html
Remove-Item .\KotlinTrainingTestProject\TestResult.txt
Remove-Item .\KotlinTrainingTestProject\TestResult.xml
