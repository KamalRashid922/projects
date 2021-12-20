import time
import pandas as pd
import numpy as np

CITY_DATA = { 'chicago': 'chicago.csv',
              'new york city': 'new_york_city.csv',
              'washington': 'washington.csv' }

def get_filters():
    """
    Asks user to specify a city, month, and day to analyze.

    Returns:
        (str) city - name of the city to analyze
        (str) month - name of the month to filter by, or "all" to apply no month filter
        (str) day - name of the day of week to filter by, or "all" to apply no day filter
    """
    print('Hello! Let\'s explore some US bikeshare data!')
    # get user input for city (chicago, new york city, washington). HINT: Use a while loop to handle invalid inputs
    city = take_input("Would you like to see data for Chicago, New York City, or Washington?\n",
                      ['chicago', 'new york city', 'washington'])

    filter_type = take_input("Would you like to filter the data by month, day, both or not at all? type none for no filter\n",
                             ['day', 'month', 'both', 'none'])

    # get user input for month (all, january, february, ... , june)
    if filter_type.lower() == 'month':
        month = take_input("Which month - January, February, March, April, May, or June? type the full name.\n",
                           ['january', 'february', 'march', 'april', 'may', 'june'])
        day = None
    # get user input for day of week (all, monday, tuesday, ... sunday)
    elif filter_type.lower() == 'day':
        day = take_input("Which day - Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, or Sunday? type the full name.\n",
                         ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'])
        month = None
    elif filter_type.lower() == 'both':
        month = take_input("Which month - January, February, March, April, May, or June? type the full name.\n",
                           ['january', 'february', 'march', 'april', 'may', 'june'])
        day = take_input("Which day - Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, or Sunday? type the full name.\n",
                           ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'])
    else:
        month = None
        day = None
    print('-'*40)
    return city, month, day


def take_input(msg, expected__input):
    while True:
        user_input = input(msg)
        if user_input.lower() in expected__input:
            return user_input
        else:
            print("Wrong input!")



def load_data(city, month, day):
    """
    Loads data for the specified city and filters by month and day if applicable.

    Args:
        (str) city - name of the city to analyze
        (str) month - name of the month to filter by, or "all" to apply no month filter
        (str) day - name of the day of week to filter by, or "all" to apply no day filter
    Returns:
        df - Pandas DataFrame containing city data filtered by month and day
    """
    # load data file into a dataframe
    df = pd.read_csv(CITY_DATA[city])

    # convert the Start Time column to datetime
    df['Start Time'] = pd.to_datetime(df['Start Time'])

    # extract month and day of week from Start Time to create new columns
    df['month'] = [i.strftime("%B") for i in df['Start Time']]
    df['day_of_week'] = [i.strftime("%A") for i in df['Start Time']]

    # filter by month if applicable
    if month != None:
        # filter by month to create the new dataframe
        df = df[df['month'] == month.title()]

    # filter by day of week if applicable
    if day != None:
        # filter by day of week to create the new dataframe
        df = df[df['day_of_week'] == day.title()]

    return df


def time_stats(df):
    """Displays statistics on the most frequent times of travel."""

    print('\nCalculating The Most Frequent Times of Travel...\n')
    start_time = time.time()


    # display the most common month
    popular_hour = df['month'].mode()[0]
    print('Most Frequent Month:', popular_hour)

    # display the most common day of week
    popular_hour = df['day_of_week'].mode()[0]
    print('Most Frequent Day Of The Week:', popular_hour)

    # display the most common start hour
    df['hour'] = [i.strftime("%H") for i in df['Start Time']]
    popular_hour = df['hour'].mode()[0]
    print('Most Frequent Start Hour:', popular_hour)

    print("\nThis took %s seconds." % (time.time() - start_time))
    print('-'*40)


def station_stats(df):
    """Displays statistics on the most popular stations and trip."""

    print('\nCalculating The Most Popular Stations and Trip...\n')
    start_time = time.time()

    # display most commonly used start station
    popular_start_station = df['Start Station'].mode()[0]
    print("most commonly used start station: ", popular_start_station)

    # display most commonly used end station
    popular_end_station = df['End Station'].mode()[0]
    print("most commonly used end station: ", popular_end_station)

    # display most frequent combination of start station and end station trip
    combination = zip(df['Start Station'], df['End Station'])
    popular_combination = pd.Series(list(combination)).mode()[0]
    print("most frequent combination of start station and end station trip: ", popular_combination)

    print("\nThis took %s seconds." % (time.time() - start_time))
    print('-'*40)


def trip_duration_stats(df):
    """Displays statistics on the total and average trip duration."""

    print('\nCalculating Trip Duration...\n')
    start_time = time.time()

    # display total travel time
    total_time = df["Trip Duration"].sum()
    print("total travel time: ", total_time)

    # display mean travel time
    average_time = df["Trip Duration"].mean()
    print("average travel time: ", average_time)

    print("\nThis took %s seconds." % (time.time() - start_time))
    print('-'*40)


def user_stats(df):
    """Displays statistics on bikeshare users."""

    print('\nCalculating User Stats...\n')
    start_time = time.time()

    # Display counts of user types
    user_types = df.groupby(['User Type'])['User Type'].count()
    print("counts of user types: ", user_types)

    # Display counts of gender
    user_types = df.groupby(['Gender'])['Gender'].count()
    print("counts of user gender: ", user_types)

    # Display earliest, most recent, and most common year of birth
    print("Most earliest year of birth: ", df['Birth Year'].min())
    print("Most recent year of birth: ", df['Birth Year'].max())
    print("Most common year of birth: ", df['Birth Year'].mode()[0])

    print("\nThis took %s seconds." % (time.time() - start_time))
    print('-'*40)


def display_RawData(df):
    df.pop('hour')
    df.pop('month')
    df.pop('day_of_week')
    j = 0
    for i in df.index:
        if j % 5 == 0:
            user_inp = take_input("Would you like to see individual trip data? type 'yes' or 'no'\n", ['yes', 'no'])
            if user_inp != 'yes':
                break
        print(df.loc[i])
        j += 1



def main():
    while True:
        city, month, day = get_filters()
        df = load_data(city, month, day)

        time_stats(df)
        station_stats(df)
        trip_duration_stats(df)
        user_stats(df)

        display_RawData(df)

        restart = input('\nWould you like to restart? Enter yes or no.\n')
        if restart.lower() != 'yes':
            break


if __name__ == "__main__":
	main()
