class Watch implements Runnable{
    private static double time = 0;
    private static int iterations;

    public void run() {
        while (iterations -- > 0) {
            try {
                Thread.sleep(25);
                time += 0.4;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void watchSetting(int trainNumber, int stationNumber) {
    	int temp = trainNumber*600 + 625*(stationNumber - 2); 
        Watch.iterations = temp/25;
    }

    static double getTime() {
        return time;
    }

    public static void startCountingTime() {
        Thread watchThread = new Thread(new Watch());
        watchThread.start();
    }
}
