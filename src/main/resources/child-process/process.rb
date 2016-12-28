require 'vertx/vertx'
require 'child-process/stream_output'
require 'child-process/stream_input'
require 'vertx/util/utils.rb'
# Generated from com.julienviet.childprocess.Process
module ChildProcess
  #  A process launched from this current process.
  class Process
    # @private
    # @param j_del [::ChildProcess::Process] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::ChildProcess::Process] the underlying java delegate
    def j_del
      @j_del
    end
    # @return [Hash{String => String}] the current process environment variables
    def self.env
      if !block_given?
        return Java::IoVertxLangRuby::Helper.adaptingMap(Java::ComJulienvietChildprocess::Process.java_method(:env, []).call(), Proc.new { |val| ::Vertx::Util::Utils.from_object(val) }, Proc.new { |val| ::Vertx::Util::Utils.to_string(val) })
      end
      raise ArgumentError, "Invalid arguments when calling env()"
    end
    #  Create and start a child process from this process.
    # @overload spawn(vertx,command)
    #   @param [::Vertx::Vertx] vertx the vertx instance
    #   @param [String] command the command to run
    # @overload spawn(vertx,command,args)
    #   @param [::Vertx::Vertx] vertx the vertx instance
    #   @param [String] command the command to run
    #   @param [Array<String>] args list of string arguments
    # @overload spawn(vertx,command,options)
    #   @param [::Vertx::Vertx] vertx the vertx instance
    #   @param [String] command the command to run
    #   @param [Hash] options the options to run the command
    # @overload spawn(vertx,command,args,options)
    #   @param [::Vertx::Vertx] vertx the vertx instance
    #   @param [String] command the command to run
    #   @param [Array<String>] args list of string arguments
    #   @param [Hash] options the options to run the command
    # @return [::ChildProcess::Process] the process
    def self.spawn(param_1=nil,param_2=nil,param_3=nil,param_4=nil)
      if param_1.class.method_defined?(:j_del) && param_2.class == String && !block_given? && param_3 == nil && param_4 == nil
        return ::Vertx::Util::Utils.safe_create(Java::ComJulienvietChildprocess::Process.java_method(:spawn, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class]).call(param_1.j_del,param_2),::ChildProcess::Process)
      elsif param_1.class.method_defined?(:j_del) && param_2.class == String && param_3.class == Array && !block_given? && param_4 == nil
        return ::Vertx::Util::Utils.safe_create(Java::ComJulienvietChildprocess::Process.java_method(:spawn, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class,Java::JavaUtil::List.java_class]).call(param_1.j_del,param_2,param_3.map { |element| element }),::ChildProcess::Process)
      elsif param_1.class.method_defined?(:j_del) && param_2.class == String && param_3.class == Hash && !block_given? && param_4 == nil
        return ::Vertx::Util::Utils.safe_create(Java::ComJulienvietChildprocess::Process.java_method(:spawn, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class,Java::ComJulienvietChildprocess::ProcessOptions.java_class]).call(param_1.j_del,param_2,Java::ComJulienvietChildprocess::ProcessOptions.new(::Vertx::Util::Utils.to_json_object(param_3))),::ChildProcess::Process)
      elsif param_1.class.method_defined?(:j_del) && param_2.class == String && param_3.class == Array && param_4.class == Hash && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::ComJulienvietChildprocess::Process.java_method(:spawn, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class,Java::JavaUtil::List.java_class,Java::ComJulienvietChildprocess::ProcessOptions.java_class]).call(param_1.j_del,param_2,param_3.map { |element| element },Java::ComJulienvietChildprocess::ProcessOptions.new(::Vertx::Util::Utils.to_json_object(param_4))),::ChildProcess::Process)
      end
      raise ArgumentError, "Invalid arguments when calling spawn(param_1,param_2,param_3,param_4)"
    end
    #  Create a child process (not running) from this process, call {::ChildProcess::Process#start} to start the process.
    # @overload create(vertx,command)
    #   @param [::Vertx::Vertx] vertx the vertx instance
    #   @param [String] command the command to run
    # @overload create(vertx,command,args)
    #   @param [::Vertx::Vertx] vertx the vertx instance
    #   @param [String] command the command to run
    #   @param [Array<String>] args list of string arguments
    # @overload create(vertx,command,options)
    #   @param [::Vertx::Vertx] vertx the vertx instance
    #   @param [String] command the command to run
    #   @param [Hash] options the options to run the command
    # @overload create(vertx,command,args,options)
    #   @param [::Vertx::Vertx] vertx the vertx instance
    #   @param [String] command the command to run
    #   @param [Array<String>] args list of string arguments
    #   @param [Hash] options the options to run the command
    # @return [::ChildProcess::Process] the created child process
    def self.create(param_1=nil,param_2=nil,param_3=nil,param_4=nil)
      if param_1.class.method_defined?(:j_del) && param_2.class == String && !block_given? && param_3 == nil && param_4 == nil
        return ::Vertx::Util::Utils.safe_create(Java::ComJulienvietChildprocess::Process.java_method(:create, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class]).call(param_1.j_del,param_2),::ChildProcess::Process)
      elsif param_1.class.method_defined?(:j_del) && param_2.class == String && param_3.class == Array && !block_given? && param_4 == nil
        return ::Vertx::Util::Utils.safe_create(Java::ComJulienvietChildprocess::Process.java_method(:create, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class,Java::JavaUtil::List.java_class]).call(param_1.j_del,param_2,param_3.map { |element| element }),::ChildProcess::Process)
      elsif param_1.class.method_defined?(:j_del) && param_2.class == String && param_3.class == Hash && !block_given? && param_4 == nil
        return ::Vertx::Util::Utils.safe_create(Java::ComJulienvietChildprocess::Process.java_method(:create, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class,Java::ComJulienvietChildprocess::ProcessOptions.java_class]).call(param_1.j_del,param_2,Java::ComJulienvietChildprocess::ProcessOptions.new(::Vertx::Util::Utils.to_json_object(param_3))),::ChildProcess::Process)
      elsif param_1.class.method_defined?(:j_del) && param_2.class == String && param_3.class == Array && param_4.class == Hash && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::ComJulienvietChildprocess::Process.java_method(:create, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class,Java::JavaUtil::List.java_class,Java::ComJulienvietChildprocess::ProcessOptions.java_class]).call(param_1.j_del,param_2,param_3.map { |element| element },Java::ComJulienvietChildprocess::ProcessOptions.new(::Vertx::Util::Utils.to_json_object(param_4))),::ChildProcess::Process)
      end
      raise ArgumentError, "Invalid arguments when calling create(param_1,param_2,param_3,param_4)"
    end
    #  Start the process.
    # @yield the handler to be called when the process has started
    # @return [void]
    def start
      if !block_given?
        return @j_del.java_method(:start, []).call()
      elsif block_given?
        return @j_del.java_method(:start, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |event| yield(::Vertx::Util::Utils.safe_create(event,::ChildProcess::Process)) }))
      end
      raise ArgumentError, "Invalid arguments when calling start()"
    end
    #  Set the handler to be called when the process exits, the handler will be called with the
    #  process status code value.
    # @yield the handler
    # @return [self]
    def exit_handler
      if block_given?
        @j_del.java_method(:exitHandler, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |event| yield(event) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling exit_handler()"
    end
    # @return [Fixnum] the process PID or null if the process is not running
    def pid
      if !block_given?
        return @j_del.java_method(:pid, []).call()
      end
      raise ArgumentError, "Invalid arguments when calling pid()"
    end
    # @return [::ChildProcess::StreamOutput] the process stdin stream
    def stdin
      if !block_given?
        if @cached_stdin != nil
          return @cached_stdin
        end
        return @cached_stdin = ::Vertx::Util::Utils.safe_create(@j_del.java_method(:stdin, []).call(),::ChildProcess::StreamOutput)
      end
      raise ArgumentError, "Invalid arguments when calling stdin()"
    end
    # @return [::ChildProcess::StreamInput] the process stdout stream
    def stdout
      if !block_given?
        if @cached_stdout != nil
          return @cached_stdout
        end
        return @cached_stdout = ::Vertx::Util::Utils.safe_create(@j_del.java_method(:stdout, []).call(),::ChildProcess::StreamInput)
      end
      raise ArgumentError, "Invalid arguments when calling stdout()"
    end
    # @return [::ChildProcess::StreamInput] the process stderr stream
    def stderr
      if !block_given?
        if @cached_stderr != nil
          return @cached_stderr
        end
        return @cached_stderr = ::Vertx::Util::Utils.safe_create(@j_del.java_method(:stderr, []).call(),::ChildProcess::StreamInput)
      end
      raise ArgumentError, "Invalid arguments when calling stderr()"
    end
    #  Terminates the process.
    #  <p>
    #  If <code>force</code> is <code>false</code>, the process will be terminated gracefully (i.e. its shutdown logic will
    #  be allowed to execute), assuming the OS supports such behavior. Note that the process may not actually
    #  terminate, as its cleanup logic may fail or it may choose to ignore the termination request. If a guarantee
    #  of termination is required, call this method with force equal to true instead.
    #  <p>
    #  If <code>force</code> is <code>true</code>, the process is guaranteed to terminate, but whether it is terminated
    #  gracefully or not is OS-dependent. Note that it may take the OS a moment to terminate the process, so
    #  {::ChildProcess::Process#is_running} may return <code>true</code> for a brief period after calling this method.
    # @param [true,false] force if true is passed, the process will be forcibly killed
    # @return [void]
    def destroy(force=nil)
      if (force.class == TrueClass || force.class == FalseClass) && !block_given?
        return @j_del.java_method(:destroy, [Java::boolean.java_class]).call(force)
      end
      raise ArgumentError, "Invalid arguments when calling destroy(force)"
    end
    #  Tests whether or not the process is still running or has exited.
    # @return [true,false]
    def running?
      if !block_given?
        return @j_del.java_method(:isRunning, []).call()
      end
      raise ArgumentError, "Invalid arguments when calling running?()"
    end
  end
end
