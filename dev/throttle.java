        Queue<AsyncContext> jobQueue = new ConcurrentLinkedQueue<AsyncContext>();
        sce.getServletContext().setAttribute("slowWebServiceJobQueue", jobQueue);
        // pool size matching Web services capacity
        Executor executor = Executors.newFixedThreadPool(10);
        while(true)
        {
            if(!jobQueue.isEmpty())
            {
                final AsyncContext aCtx = jobQueue.poll();
                executor.execute(new Runnable(){
                    public void run() {
                        ServletRequest request = aCtx.getRequest();
                        // get parameteres
                        // invoke a Web service endpoint
                        // set results
                        aCtx.forward("/result.jsp");
                    }                    
                });             
            }
        }
