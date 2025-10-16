
 # ** Avi's Accounting Ledger Application **

A command-line accounting ledger application built with Java that makes it easy to track deposits, and payments. Whether you're managing personal finances or tracking business expenses, this app provides a simple yet powerful way to organize and analyze your financial data.

## Features

### Add & Track Transactions
- Record deposits and payments quickly through an intuitive interface
- Each transaction is automatically saved with the date, time, vendor, description, and amount
- Verify entries before saving to ensure accuracy

### View Your Ledger
- **All Transactions** - See your complete financial history
- **Deposits Only** - Filter to show just deposits
- **Payments Only** - View all payments 
- All Entries Displayed in order 

### Reports & Filtering
The Reports screen gives you multiple ways to analyze your data:

**Quick Filters:**
- Month to Date
- Previous Month
- Year to Date
- Previous Year
- Search by Vendor

**Custom Search:**
Create advanced searches with your own criteria:
- Custom date ranges (start and end dates)
- Description keywords
- Vendor name
- Transaction amount

## Screenshots


- Welcome Screen
  
![Welcome Screen](https://github.com/user-attachments/assets/140aeb4e-083c-401b-8550-e055ada76153)

- Home Screen
  
![Home Screen](https://github.com/user-attachments/assets/830733b5-63ff-47ea-ba40-c90c87809d29)

- Deposit Entry
  
![Deposit Entry](https://github.com/user-attachments/assets/e34f26b9-8993-4e45-9710-9a4f6fbfe732)

- Ledger screen
  
![Ledger Screen](https://github.com/user-attachments/assets/ebe639cc-45a5-43e2-a4e1-18f92285c5ed)

- Reports Screen
  
![Reports Screen](https://github.com/user-attachments/assets/7c700ae9-94e4-4ecb-a83a-220b36f704e0)

- Filter Example
  
![Year To Date Filter](https://github.com/user-attachments/assets/87425e6c-5aa1-4075-9d30-94e747d1dbb6)

- Search Vendor Example
  
![Search by Vendor](https://github.com/user-attachments/assets/b49dd108-7d22-4120-9565-9b6220bad029)

- Custom Search With Reentry Example
  
![Custom Search Flow With Retrial entry](https://github.com/user-attachments/assets/2c48db2f-a755-4a31-8e3e-8cfd38aafe64)


## How It Works

1. **Home Screen** - Start here to choose your action:
   - Add a deposit
   - Make a payment
   - View your ledger
   - Exit the application

2. **Add Transactions** - Enter deposit or payment details and they're automatically saved

3. **View Ledger** - See all your transactions organized by category

4. **Generate Reports** - Use built-in filters or create custom searches to analyze your spending

## Data Storage

All transactions are stored in a `transactions.csv` file with the following format:

date|time|description|vendor|amount

