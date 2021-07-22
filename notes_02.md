###Redis
* 概念: redis是一款高性能的NOSQL系列的非关系型数据库
* 命令操作
  1. redis的数据结构: key-value格式的数据, 其中key都是字符串, value有5种不同的数据结构
     * value的数据结构:
       1. 字符串类型 string
            ```shell
              #存储
              set key value
              #获取
              get key
              #删除
              del key
            ```
       2. 哈希类型 hash
          ```shell
              #存储
              hset key field value
              #获取指定的field对应的值
              hget key field
              #获取所有的field和value
              hgetall key 
              #删除
              hdel key field
          ```
       3. 列表类型 list (允许重复)
          ```shell
            #将元素添加到列表的左边
            lpush key value
            #将元素添加到列表的右边
            rpush key value
            #获取
            lrange key start end  (获取所有  lrange 0 -1)
            #删除列表最左边的元素, 并将元素返回
            lpop key
            #删除列表最右边的元素, 并将元素返回
            rpop key
            #删除列表最左边的元素, 并将元素返回
            lpop key
          ``` 
       4. 集合类型 set (不能重复)
          ```shell
            #存储
            sadd key value
            #获取set集合中所有元素
            smembers key
            #删除集合中的某个value元素
            srem key value
          ```
       5. 有序集合类型 sortedset (有序且不重复)
          ```shell
             #存储时按照score排序
             zadd key score value
             #获取
             zrangge key start end 
             #删除
             zrem key value
          ```
          
    2. 通用命令
    ```shell
      #查询所有的键
      keys * 
      #查询key对应value数据类型
      type key
      #删除指定的key value
      del key
    ```
  
* 持久化 (Redis是一个内存数据库, 我们可以将redis内存中的数据持久化保存到硬盘的文件中) 
    + 持久化机制
      1. RDB: 默认方式, 在一定的间隔时间中, 检测key的变化情况, 然后持久化数据
      2. AOF: 日志记录的方式, 可以记录每一条命令的操作, 可以做到每次修改都进行持久化, 对性能消耗较大 `appendonly yes`