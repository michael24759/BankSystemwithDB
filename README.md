# Bank System with Database
an Object Oriented Programming project before the final-exam  

Professor:  
D6421 - MUHAMMAD FIKRI HASANI, S.Kom., M.T.  

Students:  
2440022826 - MICHAEL LEONARDO 

Updated from project [BankSystem](https://github.com/michael24759/BankSystem)  

## Changes
Changed Account class into abstract and adding two new account which is BasicAccount and DepositOnlyAccount.  
Added two new interface for Deposit and Withdraw method.  
BasicAccount is the same as Account from previous project, while DepositOnlyAccount is a BasicAccount that can only do deposit.  
This DepositOnlyAccount is an example of an account for company to pay their employee.  

#### Added Database
Added database using phpMyAdmin to store data of Client, BasicAccount and StrictAccount.  
The database creation can be found in src/banksystem.sql  
BankSystem.java is the main file of the program.
