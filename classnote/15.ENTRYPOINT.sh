FROM nginx

ENTRYPOINT ["nginx", "-c"]
CMD ["/etc/nginx/nginx.conf"]

#  是否传参                 按照dockerfile编写执行                传参运行
#  docker命令              docker run nginx:test               docker run nginx:test -c /etc/nginx/new.conf
# 衍生出的实际命令           nginx -c /etc/nginx/nginx.conf      nginx -c /etc/nginx/new.conf

#解释一下
#使用dockerfile执行时,此时ENTRYPOINT和CMD连用,所以此时CMD会传参给ENTRYPOINT,所以就变成ENTRYPOINT的第一个参数是nginx表示命令,第二个参数是-c,而第三个参数就是CMD传递来的了
#而传参运行时,此时run后的参数:/etc/nginx/new.conf 会覆盖掉原有的CMD中的内容,所以CMD实际上变成了 CMD ["/etc/nginx/new.conf"],所以此时的实际命令也就随之发生了变动